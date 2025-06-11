
<template>
  <view class="container">
    <view class="grid-container">
      <button 
        v-for="(item, index) in grids" 
        :key="index"
        class="grid-item"
        :style="{ backgroundColor: item.color }"
        @click="handleClick(index)"
      >
        {{ item.text }}
      </button>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const grids = ref(Array(9).fill().map(() => ({
  color: '',
  text: '',
  isTarget: false
})))

const targetCount = 2
let timer = null

// 初始化游戏
const initGame = () => {
  // 重置所有格子
  grids.value = grids.value.map(item => ({
    ...item,
    color: '',
    text: '',
    isTarget: false
  }))
  
  // 随机选择targetCount个格子
  const targets = []
  while(targets.length < targetCount) {
    const randomIndex = Math.floor(Math.random() * 9)
    if(!targets.includes(randomIndex)) {
      targets.push(randomIndex)
      grids.value[randomIndex].isTarget = true
    }
  }
  
  // 设置随机颜色
  targets.forEach(index => {
    grids.value[index].color = getRandomColor()
  })
  
  // 1秒后清除颜色
  timer = setTimeout(() => {
    grids.value.forEach(item => {
      item.color = ''
    })
  }, 1000)
}

const getRandomColor = () => {
  const letters = '0123456789ABCDEF'
  let color = '#'
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)]
  }
  return color
}

const handleClick = (index) => {
  if(grids.value[index].isTarget) {
    uni.showToast({
      title: '你成功了！',
      icon: 'success'
    })
  } else {
    uni.showToast({
      title: '你失败了',
      icon: 'error'
    })
  }
  clearTimeout(timer)
  setTimeout(initGame, 1500)
}

onMounted(() => {
  initGame()
})
</script>

<style>
.container {
  padding: 20px;
  display: flex;
  justify-content: center;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
  width: 300px;
}

.grid-item {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  border: 1px solid #eee;
  transition: background-color 0.3s;
}
</style>