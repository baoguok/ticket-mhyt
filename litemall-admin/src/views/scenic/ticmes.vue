<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <!-- <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button> -->
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <!-- <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button> -->
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="mesId"/>

      <el-table-column align="center" label="景区名称" prop="mesName"/>

      <el-table-column align="center" label="开放时间" prop="mesOpen"/>

      <el-table-column align="center" label="预定须知" prop="mesNotice"/>

      <el-table-column align="center" label="特别提示" prop="mesTitle"/>

      <el-table-column align="center" label="优待政策" prop="mesOffer"/>

      <el-table-column align="center" label="建议时长" prop="mesSugTime"/>

      <el-table-column align="center" label="景区地址" prop="mesAddress"/>

      <el-table-column align="center" label="景区设施" prop="mesFacility"/>

      <el-table-column align="center" label="联系人电话" prop="mesField"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="mesUpdateTime"/>

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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="38%" >
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin:auto;">
        <el-form-item label="景区名称" prop="mesName">
          <el-input v-model="dataForm.mesName"/>
        </el-form-item>
        <el-form-item label="开放时间" prop="mesOpen">
          <el-input v-model="dataForm.mesOpen"/>
        </el-form-item>
        <el-form-item label="预定须知" prop="mesNotice">
          <el-input v-model="dataForm.mesNotice"/>
        </el-form-item>
        <el-form-item label="特别提示" prop="mesTitle">
          <el-input v-model="dataForm.mesTitle"/>
        </el-form-item>
        <el-form-item label="优待政策" prop="mesOffer">
          <el-input v-model="dataForm.mesOffer"/>
        </el-form-item>
        <el-form-item label="建议时长" prop="mesSugTime">
          <el-input v-model="dataForm.mesSugTime"/>
        </el-form-item>
        <el-form-item label="景区地址" prop="mesAddress">
          <el-input v-model="dataForm.mesAddress"/>
        </el-form-item>
        <el-form-item label="景区设施" prop="mesFacility">
          <el-input v-model="dataForm.mesFacility"/>
        </el-form-item>
        <el-form-item label="联系人电话" prop="mesField">
          <el-input v-model="dataForm.mesField"/>
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
import { list, create, update, delect } from '@/api/ticmes'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'TicMes',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {},
      downloadLoading: false
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
                if (v.mesId === this.dataForm.mesId) {
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
        mesId: undefined,
        mesTitle: '',
        mesSubtitle: '',
        mesContent: '',
        mesSortOrder: '',
        mesAddTime: '',
        mesUpdateTime: '',
        mesDeleted: 0
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
