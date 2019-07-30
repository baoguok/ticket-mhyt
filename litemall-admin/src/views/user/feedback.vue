<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.feeUsername" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-input v-model="listQuery.feeId" clearable class="filter-item" style="width: 200px;" placeholder="请输入反馈ID"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="反馈ID" prop="feeId"/>

      <el-table-column align="center" label="用户名" prop="feeUsername"/>

      <el-table-column align="center" label="手机号码" prop="feeMobile"/>

      <el-table-column align="center" label="反馈类型" prop="feeType"/>

      <el-table-column align="center" label="反馈内容" prop="feeContent"/>

      <!-- <el-table-column align="center" label="反馈图片" prop="feePicUrls">
        <template slot-scope="scope">
          <img v-for="item in scope.row.feePicUrls" :key="item" :src="item" width="40">
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="时间" prop="feeAddTime"/>

      <el-table-column align="center" label="操作" width="100" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleQuery(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="反馈详情" :visible.sync="dialogFormVisible" width="60%">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="120px" style="width: 680px; margin-left:50px;">
        <el-form-item label="用户名" prop="feeUsername">
          <el-input v-model="dataForm.feeUsername"/>
        </el-form-item>
        <el-form-item label="手机号码" prop="feeMobile">
          <el-input v-model="dataForm.feeMobile"/>
        </el-form-item>
        <el-form-item label="反馈类型" prop="feeType">
          <el-input v-model="dataForm.feeType"/>
        </el-form-item>
        <el-form-item label="反馈内容" prop="feeContent">
          <el-input v-model="dataForm.feeContent" :rows="4" type="textarea"/>
        </el-form-item>
        <el-form-item label="反馈图片" prop="feePicUrls">
          <img v-for="item in dataForm.feePicUrls" :key="item" :src="item" style="margin-left:10px;" width="120">
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">返回</el-button>
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
</style>

<script>
import { listFeedback } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Feedback',
  components: { Pagination },
  data() {
    return {
      list: undefined,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        sort: 'fee_add_time',
        order: 'desc'
      },
      dataForm: {
        feeId: undefined,
        feeUsername: undefined,
        feeMobile: undefined,
        feeType: undefined,
        feeContent: undefined,
        feePicUrls: []
      },
      downloadLoading: false,
      dialogFormVisible: false,
      backDetail: ''
    }
  },
  created() {
    this.getList()
  },
  methods: {
    showDetail(detail) {
      this.goodsDetail = detail
      this.detailDialogVisible = true
    },
    getList() {
      this.listLoading = true
      listFeedback(this.listQuery).then(response => {
        this.list = response.data.data.items
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleQuery(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['反馈ID', '用户名称', '反馈内容', '反馈图片列表', '反馈时间']
        const filterVal = ['id', 'username', 'content', 'picUrls', 'addTime']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '意见反馈信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
