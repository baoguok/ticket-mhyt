<template>
  <div>
    <div class="head">
  <ul >
    <li class="prev" @click="prev">❮</li>
    <li class="next" @click="next">❯</li>
    <li style="text-align:center" class="yearmoth">
      {{ Year }}年{{ Month }}月
    </li>
  </ul>
</div>
<ul class="weekdays">
  <li v-for="(item, index) in weeks" :key="index">
    {{ item.week }}
  </li>
</ul>
    <ul :animation="animationData" class="days">
      <li v-for="(item, index) in items" :key="index" @click="choice(item.month, item.day)">
        <div class="calen active" v-if="date === item.day && item.month === Month">
          <div class="calen_day">
            {{ item.day }}
          </div>
          <!-- <div class="calen_price">
            ￥{{ item.price }}
          </div> -->
        </div>
        <div class="calen" v-else-if="item.month !== Month">
          <div class="calen_day">
            {{ item.day }}
          </div>
          <!-- <div class="calen_price">
            ￥{{ item.price }}
          </div> -->
        </div>
        <div class="calen usual" v-else>
          <div class="calen_day">
            {{ item.day }}
          </div>
         <!-- <div class="calen_price">
          ￥{{ item.price }}
        </div> -->
        </div>
      </li>
    </ul>
    <div style=" text-align: center; width:100%"><button @click="revert">确定</button></div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      period: 3,
      items: [],
      Month: 0,
      Year: 0,
      date: 0,
      current: '',
      curmonth: '',
      animationData: {},
      weeks: [
        { week: '日' },
        { week: '一' },
        { week: '二' },
        { week: '三' },
        { week: '四' },
        { week: '五' },
        { week: '六' }
      ],
      goodprice: ''
    }
  },
  components: {
  },
  methods: {
    next: function () {
      var animation = wx.createAnimation({
        duration: 1000,
        timingFunction: 'ease-in'
      })
      animation.translateY(0).rotate(20).translateX(200).opacity(0).step()
      animation.translateY(0).translateX(0).opacity(1).rotate(0).step({duration: 10})
      if (this.Month + 1 > 12) {
        this.Month = 1
        this.Year = this.Year + 1
        this.animationData = animation.export()
      } else {
        this.Year = this.Year
        this.Month = this.Month + 1
        this.animationData = animation.export()
      }
      this.date = 1
      this.initialize()
    },
    revert: function () {
      wx.setStorageSync('datetime', this.Year + '-' + this.Month + '-' + this.date)
      wx.navigateBack()
    },
    choice: function (month, day) {
      var animation = wx.createAnimation({
        duration: 200,
        timingFunction: 'ease-in'
      })
      animation.translateY(0).rotate(0).translateX(0).opacity(1).step()
      this.animationData = animation.export()
      var newdate = Date.parse(this.Year + '-' + this.Month + '-' + day)
      var olddate = Date.parse(this.current)
      if (month === this.Month) {
        if (newdate >= olddate) {
          this.date = day
        }
      } else {
        var newtime = Date.parse(this.Year + '-' + month + '-' + day)
        var oldtime = Date.parse(this.current)
        if (newtime >= oldtime) {
          this.Month = month
          this.date = day
        }
        this.initialize()
      }
    },
    prev: function () {
      var animation = wx.createAnimation({
        duration: 1000,
        timingFunction: 'ease-in'
      })
      animation.translateY(0).rotate(-20).translateX(-200).opacity(0).step()
      animation.translateY(0).translateX(0).opacity(1).rotate(0).step({duration: 10})
      if ((this.Month - 1) === 0) {
        this.Year = this.Year - 1
        this.Month = 12
        this.animationData = animation.export()
      } else {
        var newdate = Date.parse(this.Year + '-' + (this.Month - 1))
        var olddate = Date.parse(this.curmonth)
        if (newdate > olddate) {
          this.Month = this.Month - 1
          this.animationData = animation.export()
        } else if (newdate === olddate) {
          this.Month = this.Month - 1
          this.date = new Date().getDate()
          this.animationData = animation.export()
        }
      }
      this.initialize()
    },
    initialize () {
      this.items = []
      var DayNum = new Date(this.Year, this.Month, 0).getDate() //  获取当月的总天数
      for (var i = 1; i <= DayNum; i++) {
        var week = new Date(this.Year, (this.Month - 1), i).getDay() //  获取当月当天星期数
        if (i === 1 && week > 0) {
          var Last = new Date(this.Year, (this.Month - 1), 0).getDate() //  获取上个月的总天数
          for (var j = 0; j < week; j++) {
            this.items.unshift({ month: (this.Month - 1), day: (Last - j), price: this.goodprice })
          }
        }
        this.items.push({ month: this.Month, day: i, price: this.goodprice })
        if (i === DayNum && week !== 6) {
          for (var k = 1; k <= (6 - week); k++) {
            this.items.push({ month: (this.Month + 1), day: k, price: this.goodprice })
          }
        }
      }
    }
  },
  created () {
  },
  onLoad (options) {
    this.goodprice = options.price
  },
  onShow () {
    var dataTime = new Date()
    dataTime = dataTime.getHours() < 14 ? dataTime : dataTime.setDate(dataTime.getDate() + 1)
    this.Year = new Date(dataTime).getFullYear()
    this.Month = new Date(dataTime).getMonth() + 1
    this.date = new Date(dataTime).getDate()
    this.current = this.Year + '-' + this.Month + '-' + this.date
    this.curmonth = this.Year + '-' + this.Month
    this.initialize()
  }
}
</script>
<style>

* {box-sizing:border-box;}
ul {list-style-type: none;}

.head {
  width: 100%;
  background: #1abc9c;
}

.head ul {
  margin: 0px;
  padding: 0;
}

.head ul li {
  font-size: 20px;
  text-transform: uppercase;
  letter-spacing: 3px;
  color: white;
}

.head .prev {
  float: left;
  padding-top: 10px;
  padding-left: 10px;
}

.head .next {
  float: right;
  padding-top: 10px;
  padding-right: 10px;
}

.yearmoth{
  padding: 10px 0px;
}

.weekdays {
  margin: 0;
  color: #2b222b;
  background-color: #eeeeee;
}

.weekdays li {
  padding: 10px 0px;
  display: inline-block;
  width:  14.2857142857%;
  color: #2b222b;
  text-align: center;
}

.days {
  background: #eeeeee;
  padding: 10px 0px;
}

.days li {
  list-style-type: none;
  display: inline-block;
  width:  14.2857142857%;
  text-align: center;
  font-size:12px;
  color: #777;
}

.days li .active {
  background: #1abc9c;
  color: white !important
}

.usual {
  color: #2b2b2b;
}

.usual:active {
  background-color: #21EAC2;
  color: white;
}

.calen{
  width: 100%;
  height: 100%;
}

.calen_day{
  width: 100%;
  padding-bottom: 10px;
  padding-top: 10px;
  font-size: 16px;
  font-weight: 600;
}

.calen_price{
  width: 100%;
  padding-bottom: 10px;
  font-size: 12px;
}
</style>

