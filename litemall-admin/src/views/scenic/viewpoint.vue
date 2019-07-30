<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.vieTitle" clearable class="filter-item" style="width: 200px;" placeholder="请输入标题"/>
      <el-input v-model="listQuery.vieSubtitle" clearable class="filter-item" style="width: 200px;" placeholder="请输入副标题"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <!-- <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button> -->
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="ID" prop="vieId"/>

      <el-table-column align="center" label="标题" prop="vieTitle"/>

      <el-table-column align="center" label="副标题" prop="vieSubtitle"/>

      <el-table-column align="center" label="阅读量" prop="vieRead"/>

      <el-table-column align="center" label="图片" prop="viePicUrl">
        <template slot-scope="scope" >
        <img v-for="item in scope.row.viePicUrl" :key="item" :src="item" width="50"></template>
      </el-table-column>

      <el-table-column align="center" label="排序" prop="vieSortOrder"/>

      <!-- <el-table-column align="center" label="闸机ID" prop="vieGateId"/> -->

      <el-table-column :formatter="dateFormat" align="center" label="创建时间" prop="vieAddTime"/>

      <el-table-column :formatter="dateFormat" align="center" label="更新时间" prop="vieUpdateTime"/>

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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="45%">
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 700px; margin:auto;">
        <el-form-item label="标题" prop="vieTitle">
          <el-input v-model="dataForm.vieTitle"/>
        </el-form-item>
        <el-form-item label="副标题" prop="vieSubtitle">
          <el-input v-model="dataForm.vieSubtitle"/>
        </el-form-item>
        <el-form-item label="内容" prop="vieContent">
          <editor :init="editorInit" v-model="dataForm.vieContent"/>
        </el-form-item>

        <el-form-item label="阅读量" prop="vieRead">
          <el-input v-model="dataForm.vieRead"/>
        </el-form-item>
        <el-form-item label="商品图片" prop="viePicUrl" >
          <el-upload
            :action="uploadPath"
            :headers="headers"
            :limit="1"
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
        <el-form-item label="语音" prop="vieVoiceUrl">
          <el-input v-model="dataForm.vieVoiceUrl"/>
        </el-form-item>
        <el-form-item label="排序" prop="vieSortOrder">
          <el-input v-model="dataForm.vieSortOrder"/>
        </el-form-item>
        <!-- <el-form-item label="闸机ID" prop="vieGateId">
          <el-input v-model="dataForm.vieGateId"/>
        </el-form-item> -->
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
</style>

<script>
import { listView, create, update, delect } from '@/api/viewpoint'
import { createStorage, uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Editor from '@tinymce/tinymce-vue'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'Viewpoint',
  components: { BackToTop, Pagination, Editor },
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
        vieTitle: undefined,
        vieSubtitle: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      dataForm: {
        vieId: undefined,
        vieTitle: '',
        vieSubtitle: '',
        vieContent: '',
        vieRead: '',
        viePicUrl: [],
        vieVoiceUrl: [],
        vieSortOrder: '',
        vieAddTime: '',
        vieUpdateTime: '',
        vieDeleted: 0,
        vieGateId: ''
      },
      vies: [],
      downloadLoading: false,
      editorInit: {
        language: 'zh_CN',
        convert_urls: false,
        plugins: [
          'advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'
        ],
        toolbar: [
          'searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample',
          'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'
        ],
        images_upload_handler: function(blobInfo, success, failure) {
          const formData = new FormData()
          formData.append('file', blobInfo.blob())
          createStorage(formData)
            .then(res => {
              success(res.data.data.url)
            })
            .catch(() => {
              failure('上传失败，请重新上传')
            })
        }
      }
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
    getList() {
      this.listLoading = true
      listView(this.listQuery).then(response => {
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
        this.vies.push(response.data.url)
      }
    },
    uploadOverrun: function() {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传1张图片!'
      })
    },
    handleRemove: function(file, fileList) {
      for (var i = 0; i < this.dataForm.viePicUrl.length; i++) {
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

        if (this.dataForm.viePicUrl[i] === url) {
          this.dataForm.viePicUrl.splice(i, 1)
        }
      }
      console.log(this.dataForm.viePicUrl)
    },
    createData() {
      this.dataForm.viePicUrl = this.vies
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
      this.dataForm.viePicUrl = this.vies
      console.log(this.dataForm.viePicUrl)
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          console.log(this.dataForm)
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
              this.vies = []
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
        vieId: undefined,
        vieTitle: '',
        vieSubtitle: '',
        vieContent: '',
        vieRead: '',
        viePicUrl: '',
        vieVoiceUrl: '',
        vieSortOrder: '',
        vieAddTime: '',
        vieUpdateTime: '',
        vieDeleted: 0,
        vieGateId: ''
      }
    },
    handleCreate() {
      this.viewFileList = []
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dataForm.vieVoiceUrl = []
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.viewFileList = []
      // this.vies = []
      for (var i = 0; i < this.dataForm.viePicUrl.length; i++) {
        // this.vies.push({
        //   url: this.dataForm.viePicUrl[i]
        // })
        this.viewFileList.push({
          url: this.dataForm.viePicUrl[i]
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
        const tHeader = ['主柜ID', '主柜编号', '状态', '规格', '区域', '创建时间', '更新时间']
        const filterVal = ['id', 'mainCabNum', 'status', 'standard', 'region', 'addTime', 'updateTime']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '主柜列表')
        this.downloadLoading = false
      })
    }
  }
}
</script>
