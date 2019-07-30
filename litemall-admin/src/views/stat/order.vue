<template>
  <div class="app-container">
    <ve-line :extend="chartExtend" :data="chartData" :settings="chartSettings"/>
    <el-button class="filter-item" type="primary" icon="el-icon-download" @click="getPdf()">导出</el-button>
  </div>
</template>

<script>
import html2canvas from 'html2canvas'
import JSPDF from 'jspdf'
import { statOrder } from '@/api/stat'
import VeLine from 'v-charts/lib/line'
export default {
  components: { VeLine },
  data() {
    return {
      chartData: {},
      chartSettings: {},
      chartExtend: {}
    }
  },
  created() {
    statOrder().then(response => {
      this.chartData = response.data.data
      this.chartSettings = {
        labelMap: {
          'orders': '订单量',
          'customers': '下单用户',
          'amount': '订单总额',
          'pcr': '客单价'
        }
      }
      this.chartExtend = {
        xAxis: { boundaryGap: true }
      }
    })
  },
  methods: {
    getPdf: () => {
      const pdfDom = document.querySelector('.app-container')
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
        PDF.save('订单统计.pdf')
      })
    }
  }

}
</script>
