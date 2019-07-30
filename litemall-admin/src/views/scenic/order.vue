<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.ordUserId" clearable class="filter-item" style="width: 200px;" placeholder="用户Id"/>
      <el-input v-model="listQuery.ordOrderSn" clearable class="filter-item" style="width: 200px;" placeholder="订单编号"/>
      <el-select v-model="listQuery.ordOrderStatus" clearable class="filter-item" placeholder="订单状态">
        <el-option
          v-for="item in statusList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.ordPayWay" clearable class="filter-item" placeholder="售卖方式">
        <el-option
          v-for="item in payList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-input v-model="listQuery.ordTourist" clearable class="filter-item" style="width: 200px;" placeholder="出游人姓名"/>
      <el-input v-model="listQuery.ordMobile" clearable class="filter-item" style="width: 200px;" placeholder="出游人手机"/>
      <el-input v-model="listQuery.ordUcardId" clearable class="filter-item" style="width: 200px;" placeholder="身份证"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <!-- <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button> -->
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="ordId"/>

      <el-table-column align="center" label="用户Id" prop="ordUserId"/>

      <el-table-column align="center" label="订单编号" prop="ordOrderSn"/>

      <el-table-column align="center" label="订单状态" prop="ordOrderStatus">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.ordOrderStatus | orderStatusFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="出游人姓名" prop="ordTourist"/>

      <el-table-column align="center" label="出游人手机" prop="ordMobile"/>

      <el-table-column align="center" label="身份证" prop="ordUcardId"/>

      <el-table-column align="center" label="订单费用" prop="ordOrderPrice"/>

      <el-table-column align="center" label="用户使用时间" prop="ordConfirmTime"/>

      <el-table-column align="center" label="订单关闭时间" prop="ordEndTime"/>

      <el-table-column align="center" label="售卖方式" prop="ordPayWay">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.ordPayWay | ordPayWayFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
          <el-button v-if="scope.row.ordOrderStatus==1" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <!-- 订单详情框 -->
    <el-dialog :visible.sync="orderDialogVisible" title="订单详情" width="800">
      <el-form :data="orderDetail" label-position="left">
        <el-form-item label="用户Id">
          <span>{{ orderDetail.ordUserId }}</span>
        </el-form-item>
        <el-form-item label="订单编号">
          <span>{{ orderDetail.ordOrderSn }}</span>
        </el-form-item>
        <el-form-item label="订单状态">
          <template slot-scope="scope">
            <el-tag>{{ scope.order.ordOrderStatus | orderStatusFilter }}</el-tag>
          </template>
        </el-form-item>
        <el-form-item label="出游人姓名">
          <span>{{ orderDetail.ordTourist }}</span>
        </el-form-item>
        <el-form-item label="出游人手机">
          <span>{{ orderDetail.ordMobile }}</span>
        </el-form-item>
        <el-form-item label="身份证">
          <span>{{ orderDetail.ordUcardId }}</span>
        </el-form-item>
        <el-form-item label="商品价格">
          <span>{{ orderDetail.ordGoodsPrice }}</span>
        </el-form-item>
        <el-form-item label="优惠券ID">
          <span>{{ orderDetail.ordCouponId }}</span>
        </el-form-item>
        <el-form-item label="优惠券价格">
          <span>{{ orderDetail.ordCouponPrice }}</span>
        </el-form-item>
        <el-form-item label="订单价格">
          <span>{{ orderDetail.ordOrderPrice }}</span>
        </el-form-item>
        <el-form-item label="实付价格">
          <span>{{ orderDetail.ordActualPrice }}</span>
        </el-form-item>
        <el-form-item label="订单支付Id">
          <span>{{ orderDetail.ordPayId }}</span>
        </el-form-item>
        <el-form-item label="订单支付时间">
          <span>{{ orderDetail.ordPayTime }}</span>
        </el-form-item>
        <el-form-item label="用户使用时间">
          <span>{{ orderDetail.ordConfirmTime }}</span>
        </el-form-item>
        <el-form-item label="取票码">
          <span>{{ orderDetail.ordColCode }}</span>
        </el-form-item>
        <el-form-item label="二维码">
          <span>{{ orderDetail.ordQrCode }}</span>
        </el-form-item>
        <el-form-item label="订单结束时间">
          <span>{{ orderDetail.ordEndTime }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ orderDetail.ordEndTime }}</span>
        </el-form-item>
        <el-form-item label="修改时间">
          <span>{{ orderDetail.ordEndTime }}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="38%" >
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px;margin:auto;">
        <el-form-item label="标题" prop="vieTitle">
          <el-input v-model="dataForm.vieTitle"/>
        </el-form-item>
        <el-form-item label="副标题" prop="vieSubtitle">
          <el-input v-model="dataForm.vieSubtitle"/>
        </el-form-item>
        <el-form-item label="内容" prop="vieContent">
          <el-input v-model="dataForm.vieContent"/>
        </el-form-item>
        <el-form-item label="阅读量" prop="vieRead">
          <el-input v-model="dataForm.vieRead"/>
        </el-form-item>
        <el-form-item label="图片" prop="viePicUrl">
          <el-input v-model="dataForm.viePicUrl"/>
        </el-form-item>
        <el-form-item label="语音" prop="vieVoiceUrl">
          <el-input v-model="dataForm.vieVoiceUrl"/>
        </el-form-item>
        <el-form-item label="排序" prop="vieSortOrder">
          <el-input v-model="dataForm.vieSortOrder"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
  .table-expand {
    font-size: 0;
  }
  .table-expand label {
    width: 100px;
    color: #99a9bf;
  }
  .table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
  }
  .el-table .cell {
    padding-left: 0px;
    padding-right: 0px;
  }
  .el-dialog__header {
    text-align: center;
  }
</style>

<script>
import { list, create, update, delect } from '@/api/ticorder'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  filters: {
    orderStatusFilter(status) {
      if (status === 101) {
        return '未付款'
      }
      if (status === 102) {
        return '用户取消'
      }
      if (status === 103) {
        return '系统取消'
      }
      if (status === 201) {
        return '已付款'
      }
      if (status === 202) {
        return '申请付款'
      }
      if (status === 203) {
        return '已退款'
      }
      if (status === 301) {
        return '已使用'
      }
    },
    ordPayWayFilter(status) {
      if (status) {
        return '线下'
      } else {
        return '线上'
      }
    }
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        ordUserId: undefined,
        ordOrderSn: undefined,
        ordOrderStatus: undefined,
        ordTourist: undefined,
        ordMobile: undefined,
        ordUcardId: undefined,
        ordPayWay: undefined
      },
      dialogFormVisible: false,
      orderDialogVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        ordId: undefined,
        ordUserId: '',
        ordOrderSn: '',
        ordOrderStatus: '',
        ordTourist: '',
        ordMobile: '',
        ordUcardId: '',
        ordGoodsPrice: '',
        ordCouponId: '',
        ordCouponPrice: '',
        ordOrderPrice: '',
        ordActualPrice: '',
        ordPayId: '',
        ordPayTime: '',
        ordConfirmTime: '',
        ordColCode: '',
        ordQrCode: '',
        ordEndTime: '',
        ordAddTime: '',
        ordUpdateTime: '',
        ordDeleted: 0
      },
      downloadLoading: false,
      statusList: [
        {
          value: 0,
          label: '未付款'
        },
        {
          value: 1,
          label: '用户取消'
        },
        {
          value: 2,
          label: '系统取消'
        },
        {
          value: 3,
          label: '已付款'
        },
        {
          value: 4,
          label: '申请付款'
        },
        {
          value: 5,
          label: '已退款'
        },
        {
          value: 6,
          label: '已使用'
        }
      ],
      payList: [
        {
          value: 0,
          label: '线上'
        },
        {
          value: 1,
          label: '线下'
        }
      ],
      orderDetail: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        this.list = response.data.data.items
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          create(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '创建成功'
              })
              this.getList()
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          update(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.vieId === this.dataForm.vieId) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新成功'
              })
              this.getList()
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleDelete(row) {
      delect(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除成功'
          })
          this.getList()
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        ordId: undefined,
        ordUserId: '',
        ordOrderSn: '',
        ordOrderStatus: '',
        ordTourist: '',
        ordMobile: '',
        ordUcardId: '',
        ordGoodsPrice: '',
        ordCouponId: '',
        ordCouponPrice: '',
        ordOrderPrice: '',
        ordActualPrice: '',
        ordPayId: '',
        ordPayTime: '',
        ordConfirmTime: '',
        ordColCode: '',
        ordQrCode: '',
        ordEndTime: '',
        ordAddTime: '',
        ordUpdateTime: '',
        ordDeleted: 0
      }
    },
    handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleDetail(row) {
      this.orderDetail = row
      this.orderDialogVisible = true
    },
    dateFormat(row, column, cellValue, index) {
      var str = row[column.property]
      var dateee = new Date(str).toJSON()
      return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['订单ID', '订单编号', '订单状态(101:未付款,102:用户取消,103:系统取消,201:已付款,202:申请付款,203:已退款,301:已使用)', '出游人姓名', '出游人手机', '身份证', '商品价格', '优惠券ID', '优惠券价格', '订单价格', '实付价格', '订单支付Id', '订单支付时间', '取票码']
        const filterVal = ['ordUserId', 'ordOrderSn', 'ordOrderStatus', 'ordTourist', 'ordMobile', 'ordUcardId', 'ordGoodsPrice', 'ordCouponId', 'ordCouponPrice', 'ordOrderPrice', 'ordActualPrice', 'ordPayId', 'ordPayTime', 'ordColCode']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '订单详情')
        this.downloadLoading = false
      })
    }
  }
}
</script>
