<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <span class="filter-item">开始时间: </span>
      <el-date-picker :picker-options="pickerOptions" v-model="starTime" clearable class="filter-item" style="width: 150px;" align="right" type="date" placeholder="开始时间" />
      结束时间: <el-date-picker :picker-options="pickerOptions" v-model="endTime" clearable class="filter-item" style="width: 150px;" align="right" type="date" placeholder="结束时间" />
      园区: <el-select v-model="listQuery.gooCatId" clearable class="filter-item" style="width: 140px;" placeholder="全部">
        <el-option v-for="item in parkList" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
      票种: <el-select v-model="listQuery.gooIsOnSale" clearable class="filter-item" style="width: 180px;" placeholder="全部">
        <el-option v-for="item in ticketList" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
      OTA: <el-select v-model="listQuery.gooIsOnSale" clearable class="filter-item" style="width: 180px;" placeholder="全部">
        <el-option v-for="item in otaList" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="渠道名称" prop="name"/>

      <el-table-column align="center" label="预定总数" prop="bookNum"/>

      <el-table-column align="center" label="销售总数" prop="saleNum"/>

      <el-table-column align="center" label="空单率" prop="empty"/>

      <el-table-column align="center" label="销售总额" prop="saleMoney"/>

      <el-table-column align="center" label="充值总额" prop="rechargeMoney"/>>

      <el-table-column align="center" label="系统消费金额" prop="sysPsyMoney"/>

      <el-table-column align="center" label="余额" prop="balance"/>

      <el-table-column align="center" label="授信金额" prop="credit"/>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
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
</style>

<script>
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'OTA',
  components: { BackToTop, Pagination },
  data() {
    return {
      starTime: '',
      endTime: '',
      list: [
        { name: '美景', bookNum: '2143', saleNum: '2019', empty: '5.78%', saleMoney: '140251', rechargeMoney: '256354', sysPsyMoney: '140251', balance: '0', credit: '0' },
        { name: '飞图', bookNum: '1653', saleNum: '1256', empty: '2.40%', saleMoney: '120251', rechargeMoney: '200354', sysPsyMoney: '120251', balance: '0', credit: '0' },
        { name: '总计: ', bookNum: '3796', saleNum: '3275', empty: '3.65%', saleMoney: '260502', rechargeMoney: '456708', sysPsyMoney: '260502', balance: '0', credit: '0' }
      ],
      listQuery: {
        page: 1,
        limit: 20,
        gooName: undefined,
        gooCatId: undefined,
        catName: undefined,
        gooTag: undefined,
        gooIsOnSale: undefined,
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
      parkList: [
        { value: 0, label: '全部' },
        { value: 1, label: '北区' },
        { value: 2, label: '西区' },
        { value: 3, label: '南区' },
        { value: 4, label: '西区' },
        { value: 5, label: '东北角' }
      ],
      ticketList: [
        { value: 0, label: '全部' },
        { value: 1, label: '成人票' },
        { value: 2, label: '儿童票' },
        { value: 3, label: '老人票' },
        { value: 4, label: '军人票' },
        { value: 5, label: '团体票' },
        { value: 6, label: '学生票' }
      ],
      otaList: [
        { value: 1, label: '美景' },
        { value: 2, label: '飞图' }
      ]
    }
  },
  created() {
  },
  methods: {
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['渠道名称', '预定总数', '销售总数', '空单率', '销售总额', '充值总额', '系统消费金额', '余额', '授信金额']
        const filterVal = ['name', 'bookNum', 'saleNum', 'empty', 'saleMoney', 'rechargeMoney', 'sysPsyMoney', 'balance', 'credit']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '主柜列表')
        this.downloadLoading = false
      })
    }
  }
}
</script>
