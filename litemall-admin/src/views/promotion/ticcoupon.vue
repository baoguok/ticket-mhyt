<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.couName" clearable class="filter-item" style="width: 200px;" placeholder="请输入优惠券标题"/>
      <el-select v-model="listQuery.couType" clearable style="width: 200px" class="filter-item" placeholder="请选择优惠券类型">
        <el-option v-for="type in typeOptions" :key="type.value" :label="type.label" :value="type.value"/>
      </el-select>
      <el-select v-model="listQuery.couStatus" clearable style="width: 200px" class="filter-item" placeholder="请选择优惠券状态">
        <el-option v-for="type in statusOptions" :key="type.value" :label="type.label" :value="type.value"/>
      </el-select>
      <el-select v-model="listQuery.couGoodsType" clearable style="width: 200px" class="filter-item" placeholder="请选择限制类型">
        <el-option v-for="type in goodTypeOptions" :key="type.value" :label="type.label" :value="type.value"/>
      </el-select>
      <el-button v-permission="['GET /admin/ticcoupon/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/ticcoupon/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="优惠券ID" prop="couId" sortable/>

      <el-table-column align="center" label="优惠券名称" prop="couName"/>

      <el-table-column align="center" label="介绍" prop="couDesc"/>

      <el-table-column align="center" label="标签" prop="couTag"/>

      <el-table-column align="center" label="最低消费" prop="couMin">
        <template slot-scope="scope">满{{ scope.row.couMin }}元可用</template>
      </el-table-column>

      <el-table-column align="center" label="满减金额" prop="couDiscount">
        <template slot-scope="scope">减免{{ scope.row.couDiscount }}元</template>
      </el-table-column>

      <el-table-column align="center" label="限领" prop="couLimit">
        <template slot-scope="scope">{{ scope.row.couLimit != 0 ? scope.row.couLimit : "不限" }}</template>
      </el-table-column>

      <el-table-column align="center" label="商品使用范围" prop="couGoodsType">
        <template slot-scope="scope">
          {{ scope.row.couGoodsType | formatGoodsType }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="优惠券类型" prop="couType">
        <template slot-scope="scope">
          {{ scope.row.couType | formatType }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="优惠券数量" prop="couTotal">
        <template slot-scope="scope">{{ scope.row.couTotal != 0 ? scope.row.couTotal : "不限" }}</template>
      </el-table-column>

      <el-table-column align="center" label="状态" prop="couStatus">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.couStatus | formatStatus }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button v-permission="['GET /admin/coupon/read']" type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button> -->
          <el-button v-permission="['POST /admin/coupon/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/coupon/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="38%">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="120px" style="width: 470px; margin:auto">
        <el-form-item label="优惠券名称" prop="couName">
          <el-input v-model="dataForm.couName"/>
        </el-form-item>
        <el-form-item label="介绍" prop="couDesc">
          <el-input v-model="dataForm.couDesc"/>
        </el-form-item>
        <el-form-item label="标签" prop="couTag">
          <el-input v-model="dataForm.couTag"/>
        </el-form-item>
        <el-form-item label="最低消费" prop="couMin">
          <el-input v-model="dataForm.couMin">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="满减金额" prop="couDiscount">
          <el-input v-model="dataForm.couDiscount">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="每人限领" prop="couLimit">
          <el-input v-model="dataForm.couLimit">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="分发类型" prop="couType" >
          <el-select v-model="dataForm.couType" style="width:100%;">
            <el-option
              v-for="type in typeOptions"
              :key="type.value"
              :label="type.label"
              :value="type.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券数量" prop="couTotal">
          <el-input v-model="dataForm.couTotal">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="有效期">
          <el-radio-group v-model="dataForm.couTimeType">
            <el-radio-button :label="0" >领券相对天数</el-radio-button>
            <el-radio-button :label="1" s>指定绝对时间</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="dataForm.couTimeType === 0">
          <el-input v-model="dataForm.couDays">
            <template slot="append">天</template>
          </el-input>
        </el-form-item>
        <el-form-item v-show="dataForm.couTimeType === 1">
          <el-col :span="11">
            <el-date-picker v-model="dataForm.couStartTime" type="datetime" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"/>
          </el-col>
          <el-col :span="2" class="line"> 至</el-col>
          <el-col :span="11">
            <el-date-picker v-model="dataForm.couEndTime" type="datetime" placeholder="选择日期" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"/>
          </el-col>
        </el-form-item>
        <el-form-item label="优惠券限制范围">
          <el-radio-group v-model="dataForm.couGoodsType">
            <el-radio-button :label="0">全场通用</el-radio-button>
            <el-radio-button :label="1">指定类目</el-radio-button>
            <!-- <el-radio-button :label="2">指定门票</el-radio-button> -->
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="dataForm.couGoodsType === 1">
          <el-select v-model="dataForm.couGoodsValue" multiple placeholder="请选择类目" style="width: 100%;">
            <el-option
              v-for="item in listCate"
              :key="item.catId"
              :label="item.catName"
              :value="item.catId"/>
          </el-select>
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
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
.el-dialog__header {
    text-align: center;
  }
</style>

<script>
import { list, create, update, delect } from '@/api/ticcoupon'
import { listCate } from '@/api/ticcate'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const defaultTypeOptions = [
  {
    label: '通用领券',
    value: 0
  },
  {
    label: '注册赠券',
    value: 1
  },
  {
    label: '兑换码赠券',
    value: 2
  }
]

const defaultStatusOptions = [
  {
    label: '正常',
    value: 0
  },
  {
    label: '已过期',
    value: 1
  },
  {
    label: '已下架',
    value: 2
  }
]

const defaultGoodTypeOptions = [
  {
    label: '全门票',
    value: 0
  },
  {
    label: '类目限制',
    value: 1
  },
  {
    label: '门票限制',
    value: 2
  }
]

export default {
  name: 'Coupon',
  components: { Pagination },
  filters: {
    formatType(type) {
      for (let i = 0; i < defaultTypeOptions.length; i++) {
        if (type === defaultTypeOptions[i].value) {
          return defaultTypeOptions[i].label
        }
      }
      return ''
    },
    formatGoodsType(goodsType) {
      if (goodsType === 0) {
        return '全门票通用'
      } else if (goodsType === 1) {
        return '指定分类'
      } else {
        return '指定门票'
      }
    },
    formatStatus(status) {
      if (status === 0) {
        return '正常'
      } else if (status === 1) {
        return '已过期'
      } else {
        return '已下架'
      }
    }
  },
  data() {
    return {
      typeOptions: Object.assign({}, defaultTypeOptions),
      statusOptions: Object.assign({}, defaultStatusOptions),
      goodTypeOptions: Object.assign({}, defaultGoodTypeOptions),
      list: undefined,
      listCate: [],
      total: 0,
      listLoading: true,
      cateQuery: {
        page: 1,
        limit: 100
      },
      listQuery: {
        page: 1,
        limit: 20,
        couName: undefined,
        couType: undefined,
        couStatus: undefined,
        couGoodsType: undefined
      },
      dataForm: {
        couId: undefined,
        couName: undefined,
        couDesc: undefined,
        couTag: undefined,
        couTotal: 0,
        couDiscount: 0,
        couMin: 0,
        couLimit: 1,
        couType: 0,
        couStatus: 0,
        couGoodsType: 0,
        couGoodsValue: [],
        couTimeType: 0,
        couDays: 0,
        couStartTime: null,
        couEndTime: null,
        couAddTime: null,
        couUpdateTime: null
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        name: [
          { required: true, message: '优惠券标题不能为空', trigger: 'blur' }
        ]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
    this.queryTicCate()
  },
  methods: {
    // 查询门票类目
    queryTicCate() {
      listCate(this.cateQuery).then(res => {
        this.listCate = res.data.data.items
      }).catch(() => {
        this.listCate = []
      })
    },
    getList() {
      this.listLoading = true
      list(this.listQuery)
        .then(response => {
          this.list = response.data.data.items
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        couId: undefined,
        couName: undefined,
        couDesc: undefined,
        couTag: undefined,
        couTotal: 0,
        couDiscount: 0,
        couMin: 0,
        couLimit: 1,
        couType: 0,
        couStatus: 0,
        couGoodsType: 0,
        couGoodsValue: [],
        couTimeType: 0,
        couDays: 0,
        couStartTime: null,
        couEndTime: null,
        couAddTime: null,
        couUpdateTime: null
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
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          create(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '创建优惠券成功'
              })
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
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        if (this.dataForm.days === 0) {
          this.dataForm.daysType = 1
        } else {
          this.dataForm.daysType = 0
        }
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          update(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新优惠券成功'
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
            message: '删除优惠券成功'
          })
          const index = this.list.indexOf(row)
          this.list.splice(index, 1)
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    handleDetail(row) {
      this.$router.push({ path: '/promotion/couponDetail', query: { id: row.id }})
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = [
          '优惠券ID',
          '名称',
          '内容',
          '标签',
          '最低消费',
          '减免金额',
          '每人限领',
          '优惠券数量'
        ]
        const filterVal = [
          'id',
          'name',
          'desc',
          'tag',
          'min',
          'discount',
          'limit',
          'total'
        ]
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '优惠券信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
