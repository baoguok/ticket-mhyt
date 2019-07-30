<template>
  <div class="app-container">
    <div id="pdfDom" class="pdf-wrap" style="padding-top: 55px;background-color:#fff;">
      <!-- 用于图表组件 -->
      <ve-histogram :extend="chartExtend" :data="chartData" :settings="chartSettings"/>
    </div>
    <el-button class="filter-item" type="primary" icon="el-icon-download" @click="getPdf()">导出</el-button>
  </div>
</template>

<script>
import html2canvas from 'html2canvas'
import JSPDF from 'jspdf'
import { statMoney } from '@/api/stat'
import VeHistogram from 'v-charts/lib/histogram'

export default {
  components: {
    VeHistogram
  },
  data() {
    return {
      chartData: {},
      chartSettings: {},
      chartExtend: {},
      htmlTitle: '资金流水统计--'
    }
  },
  created() {
    statMoney().then(response => {
      this.chartData = response.data.data
      this.chartSettings = {
        labelMap: {
          'amount': '日收入'
        }
      }
      this.chartExtend = {
        xAxis: { boundaryGap: true },
        series: {
          label: { show: true, position: 'top' }
        }
      }
      const year = new Date().getFullYear()
      const month = new Date().getMonth() + 1
      const day = new Date().getDate()
      this.htmlTitle += year + '-' + month + '-' + day
    })
  },
  methods: {
    getPdf: () => {
      const pdfDom = document.querySelector('#pdfDom')
      html2canvas(pdfDom, {
        allowTaint: true
      }).then(function(canvas) {
        const contentWidth = canvas.width
        const contentHeight = canvas.height
        const pageHeight = contentWidth / 592.28 * 841.89
        let leftHeight = contentHeight
        let position = 0
        const imgWidth = 595.28
        const imgHeight = 592.28 / contentWidth * contentHeight
        const pageData = canvas.toDataURL('image/jpeg', 1.0)
        const PDF = new JSPDF('', 'pt', 'a4')
        if (leftHeight < pageHeight) {
          PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
        } else {
          while (leftHeight > 0) {
            PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
            leftHeight -= pageHeight
            position -= 841.89
            if (leftHeight > 0) {
              PDF.addPage()
            }
          }
        }
        PDF.save('资金流水统计.pdf')
      })
    }
  }
}
</script>

