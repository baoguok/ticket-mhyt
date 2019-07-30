<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.gooName" clearable class="filter-item" style="width: 180px;" placeholder="请输入门票名称"/>
      <el-select v-model="listQuery.gooCatId" clearable class="filter-item" placeholder="请输入门票类目名称">
        <el-option
          v-for="item in cateList"
          :key="item.catId"
          :label="item.catName"
          :value="item.catId"/>
      </el-select>
      <el-select v-model="listQuery.gooIsOnSale" clearable class="filter-item" style="width: 120px;" placeholder="是否上架">
        <el-option
          v-for="item in onList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.gooSelfHelp" clearable class="filter-item" style="width: 120px;" placeholder="自助取票">
        <el-option
          v-for="item in selfList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <!-- <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button> -->
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="gooId"/>

      <el-table-column align="center" label="门票名称" prop="gooName"/>

      <el-table-column align="center" label="类目名称" prop="catName"/>

      <el-table-column align="center" label="门票介绍" prop="gooDesc"/>

      <el-table-column align="center" label="门票简介" prop="gooBrief"/>

      <el-table-column align="center" label="自助取票" prop="gooSelfHelp">
        <template slot-scope="scope">
          <el-tag :type="scope.row.gooSelfHelp ? '' : 'danger' ">{{ scope.row.gooSelfHelp | selfFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否上架" prop="gooIsOnSale">
        <template slot-scope="scope">
          <el-tag :type="scope.row.gooIsOnSale ? '' : 'danger' ">{{ scope.row.gooIsOnSale | onFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="排序" prop="gooSortOrder"/>

      <el-table-column align="center" label="库存(张)" prop="gooTotal"/>

      <el-table-column align="center" label="购买限制(张)" prop="gooNumLimit"/>

      <el-table-column align="center" label="普通价格" prop="gooCounterPrice"/>

      <el-table-column align="center" label="优惠价格" prop="gooRetailPrice"/>

      <el-table-column align="center" label="操作" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <span v-if="scope.row.gooIsOnSale"><el-button type="warning" size="mini" @click="obtained(scope.row)">下架</el-button></span>
          <span v-else><el-button type="success" size="mini" @click="shelf(scope.row)">上架</el-button></span>
          <span><el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button></span>
          <span><el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button></span>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="gooTotal>0" :total="gooTotal" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="38%">
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin:auto;">
        <el-form-item label="门票名称" prop="gooName">
          <el-input v-model="dataForm.gooName"/>
        </el-form-item>
        <el-form-item label="类目名称" prop="catName">
          <el-select v-model="dataForm.gooCatId" style="width:100%;">
            <el-option
              v-for="item in cateList"
              :key="item.catId"
              :label="item.catName"
              :value="item.catId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="门票介绍" prop="gooDesc">
          <el-input v-model="dataForm.gooDesc"/>
        </el-form-item>
        <el-form-item label="门票简介" prop="gooBrief">
          <el-input v-model="dataForm.gooBrief"/>
        </el-form-item>
        <el-form-item label="是否上架" prop="gooIsOnSale">
          <el-select v-model="dataForm.gooIsOnSale" style="width:100%;">
            <el-option
              v-for="item in onList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="gooSortOrder">
          <el-input v-model="dataForm.gooSortOrder"/>
        </el-form-item>
        <el-form-item label="商品数量(库存)" prop="gooTotal">
          <el-input v-model="dataForm.gooTotal"/>
        </el-form-item>
        <el-form-item label="购买限制" prop="gooNumLimit">
          <el-input v-model="dataForm.gooNumLimit"/>
        </el-form-item>
        <el-form-item label="普通价格" prop="gooCounterPrice">
          <el-input v-model="dataForm.gooCounterPrice"/>
        </el-form-item>
        <el-form-item label="优惠价格" prop="gooRetailPrice">
          <el-input v-model="dataForm.gooRetailPrice"/>
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
  .gallery {
    width: 80px;
    margin-right: 10px;
  }
  .el-dialog__header {
    text-align: center;
  }
</style>

<script>
import { list, create, update, delect } from '@/api/ticgood'
import { listCate } from '@/api/ticcate'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  filters: {
    onFilter(status) {
      if (status) {
        return '上架'
      } else {
        return '下架'
      }
    },
    selfFilter(status) {
      if (status === 0) {
        return '可用'
      } else {
        return '不可用'
      }
    }
  },
  data() {
    return {
      list: [],
      total: 0, // 门票类目统计数量
      gooTotal: 0, // 门票统计数量
      listLoading: true,
      cateQuery: {
        page: 1,
        limit: 100
      },
      listQuery: {
        page: 1,
        limit: 20,
        gooName: undefined,
        gooCatId: undefined,
        catName: undefined,
        gooTag: undefined,
        gooIsOnSale: undefined,
        gooSelfHelp: undefined,
        gooStatus: undefined,
        gooIsretreat: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        gooId: undefined,
        gooName: '',
        gooCatId: '',
        catName: '',
        gooDesc: '',
        gooBrief: '',
        gooNumLimit: '',
        gooSelfHelp: '',
        gooIsOnSale: '',
        gooSortOrder: '',
        gooTotal: '',
        gooCounterPrice: '',
        gooRetailPrice: '',
        refName: '',
        gooAddTime: '',
        gooUpdateTime: '',
        gooDeleted: 0
      },
      downloadLoading: false,
      onList: [
        {
          value: true,
          label: '上架'
        },
        {
          value: false,
          label: '下架'
        }
      ],
      selfList: [
        {
          value: 0,
          label: '可自助取票'
        },
        {
          value: 1,
          label: '不可自助取票'
        }
      ],
      isNewList: [
        {
          value: true,
          label: '否'
        },
        {
          value: false,
          label: '是'
        }
      ],
      viewList: [],
      cateList: []
    }
  },
  created() {
    this.getList()
    this.queryCate()
    this.queryView()
  },
  methods: {
    // 查询类目信息
    queryCate() {
      listCate(this.cateQuery).then(response => {
        this.cateList = response.data.data.items
        this.total = response.data.data.total
      }).catch(() => {
        this.cateList = []
        this.total = 0
      })
    },
    // 上架门票
    shelf(row) {
      console.log('上架')
      this.dataForm = Object.assign({}, row)
      this.dataForm.gooIsOnSale = true
      update(this.dataForm)
        .then(() => {
          for (const v of this.list) {
            if (v.gooId === this.dataForm.gooId) {
              const index = this.list.indexOf(v)
              this.list.splice(index, 1, this.dataForm)
              break
            }
          }
          this.dialogFormVisible = false
          this.$notify.success({
            title: '上架成功',
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
    },
    // 下架门票
    obtained(row) {
      this.dataForm = Object.assign({}, row)
      this.dataForm.gooIsOnSale = false
      update(this.dataForm)
        .then(() => {
          for (const v of this.list) {
            if (v.gooId === this.dataForm.gooId) {
              const index = this.list.indexOf(v)
              this.list.splice(index, 1, this.dataForm)
              break
            }
          }
          this.dialogFormVisible = false
          this.$notify.success({
            title: '下架成功',
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
    },
    getList() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        this.list = response.data.data.items
        this.gooTotal = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.gooTotal = 0
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
                if (v.gooId === this.dataForm.gooId) {
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
        gooId: undefined,
        gooName: '',
        gooCatId: '',
        catName: '',
        gooDesc: '',
        gooBrief: '',
        gooSelfHelp: '',
        gooIsOnSale: '',
        gooSortOrder: '',
        gooTotal: '',
        gooNumLimit: '',
        gooCounterPrice: '',
        gooRetailPrice: '',
        refName: '',
        gooAddTime: '',
        gooUpdateTime: '',
        gooDeleted: 0
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
      // this.changeSelect(row)
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },

    dateFormat(row, column, cellValue, index) {
      var str = row[column.property]
      var dateee = new Date(str).toJSON()
      return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['主柜ID', '主柜编号', '状态', '规格', '区域', '创建时间', '更新时间']
        const filterVal = ['id', 'mainCabNum', 'status', 'standard', 'region', 'addTime', 'updateTime']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '主柜列表')
        this.downloadLoading = false
      })
    }
  }
}
</script>
