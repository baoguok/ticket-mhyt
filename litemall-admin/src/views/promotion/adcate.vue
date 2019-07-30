<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.adcName" clearable class="filter-item" style="width: 200px;" placeholder="请输入类目名称"/>
      <el-input v-model="listQuery.adcKeywords" clearable class="filter-item" style="width: 200px;" placeholder="请输入关键字"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="adcId"/>

      <el-table-column align="center" label="类目名称" prop="adcName"/>

      <el-table-column align="center" label="关键字" prop="adcKeywords"/>

      <el-table-column align="center" label="排序" prop="adcSortOrder"/>

      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="adcAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="adcUpdateTime"/>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="38%">
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin:auto">
        <el-form-item label="类目名称" prop="adcName">
          <el-input v-model="dataForm.adcName"/>
        </el-form-item>
        <el-form-item label="关键字" prop="adcKeywords">
          <el-input v-model="dataForm.adcKeywords"/>
        </el-form-item>
        <el-form-item label="排序" prop="adcSortOrder">
          <el-input v-model="dataForm.adcSortOrder"/>
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
import { listadCate, create, update, delect } from '@/api/ticadcate'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        adcName: undefined,
        adcKeywords: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        adcId: undefined,
        adcName: '',
        adcKeywords: '',
        adcSortOrder: '',
        adcAddTime: '',
        adcUpdateTime: '',
        adcDeleted: 0
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listadCate(this.listQuery).then(response => {
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
        catId: undefined,
        catName: '',
        catKeywords: '',
        catDesc: '',
        catPicUrl: '',
        catPrice: '',
        catSortOrder: '',
        catAddTime: '',
        catUpdateTime: '',
        catDeleted: 0
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
