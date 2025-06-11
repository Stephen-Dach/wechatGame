
<template>
	
	<uni-button @click="randomizeColors" class="action-btn">开始游戏</uni-button>
	<view class="" style="margin: 0rpx auto;">
		{{redIndices.length}}
	</view>
  <view class="container">
	     
    <view class="bg">
     <button 
	
	 class="bt" 
	 v-for="(index,key) in grids " 
	  :key="key"
	 @click="change(key)"
	 :style="{ backgroundColor : index.color}"
	  :disabled="index.bollock"
	 ></button>
  </view>
  
  
  </view>
  

</template>

<script setup>
import { ref, onMounted,watch } from 'vue'

const copy = ref(Array(9).fill().map(()=>({
	color: '#ffffff',
	bollock: false
})))

const grids = ref(Array(9).fill().map(()=>({
	color: '#ffffff'
})))

const redIndices =ref([])
const count =ref(0)
  
const  randomizeColors =()=>{
	
	
	
	// 随机决定要变红的格子数量(1-5个)
	  const redCount = Math.floor(Math.random() * 8) + 1
	  
	  //选着随机变颜色的格子
	   redIndices.value = []
	  while(redIndices.value.length<redCount){
		  const randomIndex = Math.floor(Math.random()*9)
		  if(!redIndices.value.includes(randomIndex)){
			  redIndices.value.push(randomIndex)
		  }
	  }
	  //设置选中格子变红
	  redIndices.value.forEach(index=>{
		  grids.value[index].color = '#ff0000'
	  })
	  
	  copy.value = grids.value.map(index=>({...index}))
	 
	  console.log("12",redIndices.value);
	  
	  
	setTimeout(()=>{

		grids.value.forEach(item =>{
			item.color = '#ffffff'
		})
	},100)
	console.log("12",copy.value);
	
	
	
}

const change = (param)=>{

	count.value++
		
	
	if( count.value < redIndices.value.length ){
		if(!redIndices.value.includes(param)){
			count.value=0
			uni.showModal({
			    title: '提示',
			    content: '你失败了，是否重新开始',
			    success: function (res) {
			        if (res.confirm) {
			            console.log('用户点击确定');
						randomizeColors()
			        } else if (res.cancel) {
						
			            console.log('用户点击取消');
			        }
			    }
			});
			redIndices.value=[]
		}else{
			
				 grids.value[param].color = '#ff0000'
				 grids.value[param].bollock = true
				 
				 console.log(11111111);
			
		}
	}else if(count.value == redIndices.value.length && redIndices.value.length > 0 &&redIndices.value != null){
		count.value=0
		uni.showToast({
		  title: '通关了',	
		  icon: 'success'
		})
		// window.location.href = 'https://xbeibeix.com/video/BV1hq4y1s7VH';
	

		
		redIndices.value=[]
		randomizeColors()
	}else{
	
	}
	
	
}


</script>

<style>
.container {
  padding: 20px;
  display: flex;
  justify-content: center;
}

.bg{
	width: 600rpx;
	display: flex;
	flex-wrap: wrap;
}
.bt{
	width: 200rpx;
	height: 200rpx;
}

</style>