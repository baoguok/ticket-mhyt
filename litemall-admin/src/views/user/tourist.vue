<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.touTourist" clearable class="filter-item" style="width: 200px;" placeholder="请输入游客姓名"/>
      <el-input v-model="listQuery.touMobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入游客手机号"/>
      <el-input v-model="listQuery.userName" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="游客ID" prop="touId"/>

      <el-table-column align="center" label="游客名" prop="touTourist"/>

      <el-table-column align="center" label="游客手机号码" prop="touMobile"/>

      <el-table-column align="center" label="身份证" prop="touUcardId"/>

      <el-table-column align="center" label="时间" prop="touAddTime"/>

      <el-table-column align="center" label="操作" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

  </div>
</template>

<script>
import { listTourist } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Feedback',
  components: { Pagination },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        touTourist: undefined,
        touMobile: undefined,
        userName: undefined,
        sort: 'tou_add_time',
        order: 'desc'
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
      listTourist(this.listQuery).then(response => {
        this.list = response.data.data.items
        this.total = response.data.data.total
        this.listLoading = false
        console.log(response.data.data.items.content)
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
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
