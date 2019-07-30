<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-select v-model="listQuery.adCatId" clearable class="filter-item" placeholder="请选择类目">
        <el-option
          v-for="item in adCateList"
          :key="item.adcId"
          :label="item.adcName"
          :value="item.adcId"/>
      </el-select>
      <el-select v-model="listQuery.adPosition" clearable class="filter-item" placeholder="请选择广告位置">
        <el-option
          v-for="item in positionList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.adEnabled" clearable class="filter-item" placeholder="请选择是否启用">
        <el-option
          v-for="item in enabledList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="adId"/>

      <el-table-column align="center" label="类目名称" prop="adcName"/>

      <el-table-column align="center" label="图片" prop="adUrl">
        <template slot-scope="scope">
          <img v-if="scope.row.adUrl" :src="scope.row.adUrl" width="60">
        </template>
      </el-table-column>

      <el-table-column align="center" label="广告位置" prop="adPosition">
        <template slot-scope="scope">
          <el-tag >{{ scope.row.adPosition | posiFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否启用" prop="adEnabled">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.adEnabled | enabledFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="adAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="adUpdateTime"/>

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
          <el-select v-model="dataForm.adCatId" style="width:100%;">
            <el-option
              v-for="item in adCateList"
              :key="item.adcId"
              :label="item.adcName"
              :value="item.adcId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="图片" prop="adUrl">
          <el-input v-model="dataForm.adUrl"/>
        </el-form-item>
        <el-form-item label="广告位置" prop="adPosition">
          <el-select v-model="dataForm.adPosition" style="width:100%;">
            <el-option
              v-for="item in positionList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否启用" prop="adPosition">
          <el-select v-model="dataForm.adEnabled" style="width:100%;">
            <el-option
              v-for="item in enabledList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="景点图片" prop="adUrl">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadAdUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.adUrl" :src="dataForm.adUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
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
  width: 145px;
  height: 145px;
  display: block;
}
.el-dialog__header {
    text-align: center;
  }
</style>

<script>
import { list, create, update, delect } from '@/api/ticad'
import { listadCate } from '@/api/ticadcate'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  filters: {
    posiFilter(status) {
      if (status === 0) {
        return '非首页'
      } else {
        return '首页'
      }
    },
    enabledFilter(status) {
      if (status) {
        return '启用'
      } else {
        return '未启用'
      }
    }
  },
  data() {
    return {
      uploadPath,
      list: [],
      adCateList: [],
      total: 0,
      listLoading: true,
      queryAdCate: {},
      listQuery: {
        page: 1,
        limit: 20,
        adCatId: undefined,
        adPosition: undefined,
        adEnabled: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        adId: undefined,
        adCatId: '',
        adcName: '',
        adUrl: '',
        adPosition: '',
        adEnabled: '',
        adAddTime: '',
        adUpdateTime: '',
        adDeleted: 0
      },
      positionList: [
        {
          value: 0,
          label: '非首页'
        },
        {
          value: 1,
          label: '首页'
        }
      ],
      enabledList: [
        {
          value: true,
          label: '启用'
        },
        {
          value: false,
          label: '未启用'
        }
      ],
      adcName: '',
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
    this.getList()
    this.queryAdcate()
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
    // 查询图片类目
    queryAdcate() {
      listadCate(this.queryAdCate).then(response => {
        this.adCateList = response.data.data.items
        this.listLoading = false
      }).catch(() => {
        this.adCateList = []
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
        adId: undefined,
        adCatId: '',
        adUrl: '',
        adPosition: '',
        adEnabled: '',
        adAddTime: '',
        adUpdateTime: '',
        adDeleted: 0
      }
    },
    uploadAdUrl: function(response) {
      this.dataForm.adUrl = response.data.url
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
