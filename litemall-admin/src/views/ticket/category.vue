<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.catName" clearable class="filter-item" style="width: 200px;" placeholder="请输入类目名称"/>
      <el-input v-model="listQuery.catKeywords" clearable class="filter-item" style="width: 200px;" placeholder="请输入关键字"/>
      <el-select v-model="listQuery.catIsOnSale" clearable class="filter-item" style="width: 120px;" placeholder="是否上架">
        <el-option
          v-for="item in onList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.catIsretreat" clearable class="filter-item" style="width: 120px;" placeholder="是否可退">
        <el-option
          v-for="item in retreatList"
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

      <el-table-column align="center" label="ID" prop="catId"/>

      <el-table-column align="center" label="类目名称" prop="catName"/>

      <el-table-column align="center" label="关键字" prop="catKeywords"/>

      <el-table-column align="center" label="类目描述" prop="catDesc"/>

      <el-table-column align="center" label="原价" prop="catPrice"/>

      <el-table-column align="center" label="是否上架" prop="catIsOnSale">
        <template slot-scope="scope">
          <el-tag :type="scope.row.catIsOnSale ? '' : 'danger' ">{{ scope.row.catIsOnSale | onFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否可退" prop="catIsretreat">
        <template slot-scope="scope">
          <el-tag :type="scope.row.catIsretreat == 0 ? '' : 'success' ">{{ scope.row.catIsretreat | retreatFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="退票天数" prop="catRetreDay"/>

      <el-table-column align="center" label="手续费(单张)" prop="catRetreCost"/>

      <el-table-column align="center" label="景点名称" prop="catViewId">
        <template slot-scope="scope">
          <el-tag v-for="vieId in scope.row.catViewId" :key="vieId" type="primary">{{ formatView(vieId) }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="排序" prop="catSortOrder"/>

      <el-table-column align="center" label="允许人数" prop="catPeopleNum"/>

      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="catAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="catUpdateTime"/>

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
        <el-form-item label="类目名称" prop="catName">
          <el-input v-model="dataForm.catName"/>
        </el-form-item>
        <el-form-item label="关键字" prop="catKeywords">
          <el-input v-model="dataForm.catKeywords"/>
        </el-form-item>
        <el-form-item label="类目描述" prop="catDesc">
          <el-input v-model="dataForm.catDesc"/>
        </el-form-item>
        <el-form-item label="原价" prop="catPrice">
          <el-input v-model="dataForm.catPrice"/>
        </el-form-item>
        <el-form-item label="是否上架" prop="catIsOnSale">
          <el-select v-model="dataForm.catIsOnSale" style="width:100%;">
            <el-option
              v-for="item in onList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="退票天数" prop="catRetreDay">
          <el-input v-model="dataForm.catRetreDay"/>
        </el-form-item>
        <el-form-item label="手续费(单张)" prop="catRetreCost">
          <el-input v-model="dataForm.catRetreCost"/>
        </el-form-item>
        <el-form-item label="是否可退" prop="catIsretreat">
          <el-select v-model="dataForm.catIsretreat" style="width:100%;">
            <el-option
              v-for="item in retreatList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="景点集合" prop="catViewId">
          <el-select v-model="dataForm.catViewId" multiple style="width:100%;">
            <el-option
              v-for="item in viewList"
              :key="item.vieId"
              :label="item.vieTitle"
              :value="item.vieId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="catSortOrder">
          <el-input v-model="dataForm.catSortOrder"/>
        </el-form-item>
        <el-form-item label="允许人数" prop="catPeopleNum">
          <el-input v-model="dataForm.catPeopleNum"/>
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
  width: 145px;
  height: 145px;
  display: block;
}
.el-dialog__header {
    text-align: center;
}
.el-select-dropdown__list {
  margin-bottom: 5px;
}

</style>

<script>
import { listCate, create, update, delect } from '@/api/ticcate'
import { uploadPath } from '@/api/storage'
import { listView } from '@/api/viewpoint'
import { getToken } from '@/utils/auth'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  filters: {
    onFilter(status) {
      if (status === 0) {
        return '上架'
      } else {
        return '下架'
      }
    },
    retreatFilter(status) {
      if (status === 0) {
        return '可退票'
      } else {
        return '不可退'
      }
    }
  },
  data() {
    return {
      uploadPath,
      list: [],
      viewList: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        catName: undefined,
        catKeywords: undefined,
        catIsretreat: undefined,
        catIsOnSale: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        catId: undefined,
        catName: '',
        catKeywords: '',
        catDesc: '',
        catPicUrl: '',
        catPrice: '',
        catSortOrder: '',
        catIsOnSale: '',
        catRetreDay: '',
        catRetreCost: '',
        catViewId: [],
        catIsretreat: '',
        catAddTime: '',
        catUpdateTime: '',
        catDeleted: 0,
        catPeopleNum: ''
      },
      onList: [
        {
          value: 0,
          label: '上架'
        },
        {
          value: 1,
          label: '下架'
        }
      ],
      retreatList: [
        {
          value: 0,
          label: '可退'
        },
        {
          value: 1,
          label: '不可退'
        }
      ],
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
    this.queryView()
  },
  methods: {
    formatView(vieId) {
      for (let i = 0; i < this.viewList.length; i++) {
        if (vieId === this.viewList[i].vieId) {
          return this.viewList[i].vieTitle
        }
      }
      return ''
    },
    // 查询景点信息
    queryView() {
      listView().then(response => {
        this.viewList = response.data.data.items
        this.total = response.data.data.total
      }).catch(() => {
        this.viewList = []
        this.total = 0
      })
    },
    getList() {
      this.listLoading = true
      listCate(this.listQuery).then(response => {
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
        catIsOnSale: '',
        catRetreDay: '',
        catRetreCost: '',
        catViewId: [],
        catIsretreat: '',
        catAddTime: '',
        catUpdateTime: '',
        catDeleted: 0,
        catPeopleNum: ''
      }
    },
    uploadPic: function(response) {
      this.dataForm.catPicUrl = response.data.url
    },
    handleRemove: function(file, fileList) {
      // 这里存在两种情况
      // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
      //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
      // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
      var url
      if (file.response === undefined) {
        url = file.url
      } else {
        url = file.response.data.url
      }

      if (this.dataForm.catPicUrl === url) {
        this.dataForm.catPicUrl = []
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
        const tHeader = ['ID', '类目名称', '关键字', '类目描述', '原价', '是否上架(0:上架,1:下架)', '是否可退(0:可退,1:不可退)', '退票天数', '手续费(单张)', '景点名称', '排序']
        const filterVal = ['catId', 'catName', 'catKeywords', 'catDesc', 'catPrice', 'catIsOnSale', 'catIsretreat', 'catRetreDay', 'catRetreCost', 'catViewId', 'catSortOrder']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '门票类目')
        this.downloadLoading = false
      })
    }
  }
}
</script>
