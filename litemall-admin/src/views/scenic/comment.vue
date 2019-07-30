<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.comOrderId" clearable class="filter-item" style="width: 200px;" placeholder="请输入订单Id"/>
      <el-input v-model="listQuery.comUserId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户Id"/>
      <el-select v-model="listQuery.comHasPicture" clearable class="filter-item" placeholder="是否有图片" >
        <el-option
          v-for="item in isPictureList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.comIsgood" clearable class="filter-item" placeholder="是否推荐" >
        <el-option
          v-for="item in isgoodList"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <!-- <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button> -->
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="comId"/>

      <el-table-column align="center" label="订单ID" prop="comOrderId"/>

      <el-table-column align="center" label="评论内容" prop="comContent"/>

      <el-table-column align="center" label="用户ID" prop="comUserId"/>

      <el-table-column align="center" label="是否有图片" prop="comHasPicture">
        <template slot-scope="scope" >
        <span>{{ scope.row.comHasPicture | formatIspic }}</span></template>
      </el-table-column>

      <el-table-column align="center" label="点赞数" prop="comLike"/>

      <el-table-column align="center" label="星级(1~5星)" prop="comStar"/>

      <el-table-column align="center" label="景点名称" prop="comViewpoint"/>

      <el-table-column align="center" label="是否推荐" prop="comIsgood">
        <template slot-scope="scope">
        <span>{{ scope.row.comIsgood | formatIsgood }}</span></template>
      </el-table-column>

      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="comAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="comUpdateTime"/>

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
        <el-form-item label="订单ID" prop="comOrderId">
          <el-input v-model="dataForm.comOrderId"/>
        </el-form-item>
        <el-form-item label="评论内容" prop="comContent">
          <el-input v-model="dataForm.comContent"/>
        </el-form-item>
        <el-form-item label="用户ID" prop="comUserId">
          <el-input v-model="dataForm.comUserId"/>
        </el-form-item>
        <el-form-item label="是否有图片" prop="comHasPicture">
          <el-select v-model="dataForm.comHasPicture" style="width:100%;" @change="changeIspic(dataForm.comHasPicture)">
            <el-option
              v-for="item in isPictureList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="图片" prop="comPicUrls">
          <el-upload
            :action="uploadPath"
            :headers="headers"
            :limit="5"
            :file-list="viewFileList"
            :on-exceed="uploadOverrun"
            :on-success="handleViewUrl"
            :on-remove="handleRemove"
            multiple
            accept=".jpg,.jpeg,.png,.gif"
            list-type="picture-card">
            <i class="el-icon-plus"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="点赞数" prop="comLike">
          <el-input v-model="dataForm.comLike"/>
        </el-form-item>
        <el-form-item label="星级(1~5星)" prop="comStar">
          <el-input v-model="dataForm.comStar"/>
        </el-form-item>
        <el-form-item label="景点名称" prop="comViewpoint">
          <el-input v-model="dataForm.comViewpoint"/>
        </el-form-item>
        <el-form-item label="是否推荐" prop="comIsgood">
          <el-select v-model="dataForm.comIsgood" style="width:100%;" @change="changeIsgood(dataForm.comIsgood)">
            <el-option
              v-for="item in isgoodList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
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
import { list, create, update, delect } from '@/api/ticcomment'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination },
  filters: {
    formatIsgood(status) {
      if (status) {
        return '推荐'
      } else {
        return '未推荐'
      }
    },
    formatIspic(status) {
      if (status) {
        return '有'
      } else {
        return '否'
      }
    }
  },
  data() {
    return {
      uploadPath,
      list: [],
      viewFileList: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        comOrderId: undefined,
        comUserId: undefined,
        comIsgood: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        comId: undefined,
        comOrderId: '',
        comContent: '',
        comUserId: '',
        comHasPicture: '',
        comPicUrls: [],
        comLike: '',
        comStar: '',
        comViewpoint: '',
        comIsgood: '',
        comAddTime: '',
        comUpdateTime: '',
        comDeleted: 0
      },
      isgoodList: [
        {
          value: false,
          label: '未推荐'
        },
        {
          value: true,
          label: '推荐'
        }
      ],
      isPictureList: [
        {
          value: false,
          label: '否'
        },
        {
          value: true,
          label: '有'
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
  },
  methods: {
    // 修改是否有图片
    changeIspic(val) {
      this.dataForm.comHasPicture = val
    },
    // 修改是否推荐
    changeIsgood(val) {
      this.dataForm.comIsgood = val
    },
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
    // 图片上传成功
    handleViewUrl(response, file, fileList) {
      if (response.errno === 0) {
        this.dataForm.comPicUrls.push(response.data.url)
      }
    },
    uploadOverrun: function() {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传5张图片!'
      })
    },
    handleRemove: function(file, fileList) {
      for (var i = 0; i < this.dataForm.comPicUrls.length; i++) {
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

        if (this.dataForm.comPicUrls[i] === url) {
          this.dataForm.comPicUrls.splice(i, 1)
        }
      }
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
        comId: undefined,
        comOrderId: '',
        comContent: '',
        comUserId: '',
        comHasPicture: '',
        comPicUrls: '',
        comLike: '',
        comStar: '',
        comViewpoint: '',
        comIsgood: '',
        comAddTime: '',
        comUpdateTime: '',
        comDeleted: 0
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
      this.viewFileList = []
      this.dataForm = Object.assign({}, row)
      for (var i = 0; i < this.dataForm.comPicUrls.length; i++) {
        this.viewFileList.push({
          url: this.dataForm.comPicUrls[i]
        })
      }
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
