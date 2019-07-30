<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-select v-model="listQuery.gateViewId" clearable class="filter-item" placeholder="景点名称" >
        <el-option
          v-for="item in viewList"
          :key="item.vieId"
          :label="item.vieTitle"
          :value="item.vieId"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <!-- <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button> -->
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="gateId" sortable/>

      <el-table-column align="center" label="景点ID" prop="gateViewId"/>

      <el-table-column align="center" label="景点名称" prop="gateViewName"/>

      <el-table-column align="center" label="闸机标识" prop="gateNum"/>

      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="gateAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="gateUpdateTime"/>

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
        <!-- <el-form-item label="景点名称" prop="gateViewName">
          <el-input v-model="dataForm.gateViewName"/>
        </el-form-item> -->
        <el-form-item label="景点名称" width="100%" prop="gateViewName">
          <el-select v-model="dataForm.gateViewId" clearable class="filter-item" placeholder="景点名称" @change="changegate(dataForm.gateViewId)">
            <el-option
              v-for="item in viewList"
              :key="item.vieId"
              :label="item.vieTitle"
              :value="item.vieId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="闸机标识" prop="gateNum">
          <el-input v-model="dataForm.gateNum"/>
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
import { listGate, createGate, updateGate, deleteGate } from '@/api/gateview'
import { listView } from '@/api/viewpoint'
import { getToken } from '@/utils/auth'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'GateView',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: [],
      viewList: [],
      total: 0,
      listLoading: true,
      listViewQuery: {
        page: 1,
        limit: 1000
      },
      listQuery: {
        page: 1,
        limit: 20,
        gateViewName: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        gateId: undefined,
        gateViewId: '',
        gateViewName: '',
        gateNum: '',
        gateAddTime: '',
        gateUpdateTime: '',
        gateDeleted: 0
      },
      downloadLoading: false
    }
  },
  computed: {
    headers() {
      return {
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.queryView()
    this.getList()
  },
  methods: {
    // 修改景点名称
    changegate(val) {
      console.log(val)
      // this.dataForm.gateViewName = val
      this.dataForm.gateViewId = val
      for (const v in this.viewList) {
        if (this.viewList[v].vieId === val) {
          this.dataForm.gateViewName = this.viewList[v].vieTitle
        }
      }
    },
    // 查询景点信息
    queryView() {
      listView(this.listViewQuery).then(response => {
        this.viewList = response.data.data.items
        console.log(this.viewList)
      }).catch(() => {
        this.viewList = []
      })
    },
    getList() {
      this.listLoading = true
      listGate(this.listQuery).then(response => {
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
          createGate(this.dataForm)
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
          console.log(valid)
          updateGate(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.gateId === this.dataForm.gateId) {
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
      deleteGate(row)
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
        gateId: '',
        gateViewId: '',
        gateViewName: '',
        gateNum: '',
        gateAddTime: '',
        gateUpdateTime: '',
        gateDeleted: 0
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
      console.log(this.dataForm)
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
        const tHeader = ['订单ID', '评论内容', '用户ID', '点赞数', '星级(1~5星)', '景点名称', '是否推荐']
        const filterVal = ['comOrderId', 'comContent', 'comUserId', 'comLike', 'comStar', 'comViewpoint', 'comIsgood']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '评论列表')
        this.downloadLoading = false
      })
    }
  }
}
</script>
