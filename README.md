# leetcode
<https://mp.weixin.qq.com/s/00yRZUaU5c5KV-yTWhoDig>

# basic
---
## 数组/链表
### [前缀和](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=2&sn=19a2609f33eadbbda1f6b75e2298d931&scene=21#wechat_redirect)
主要用在频繁取值子数组和  
1.建立从0开始穷举的查询结构  
2.通过包含关系加减获得子数组和
> 二维数组矩阵原理一样, 只是它的加减关系比一维数组多
```kotlin
val numbs = intArrayOf(1,2,3)
val preSum = mutableListOf<Int>()
preSum.add(0)

for((index, value) in numbs.withIndex()) {
    preSum.add(preSum[index] + value)
}
// [1,2] = 2 + 3 = 5
// [1,2] = [0,3)-[0,1] = 6 - 1 = 5
val ans = preSum[2 + 1] - preSum[1]
```

### [差分数组](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=3&sn=1f13cb4b49e6ff698d396220ad6c54f7&scene=21#wechat_redirect)
主要用在给子数组频繁加减值  
1.建立numbs\[i\]-numbs\[i+1\]的差分数组  
2.对区域\[i,j\]的修改变成对差分数组diff\[i\]和diff\[j+1\]的修改
```kotlin
val numbs = intArrayOf(1,2,4,5,7,6)
val diff = mutableListOf<Int>() 
diff.add(numbs[0])
for(i in 1 until numbs.size) {
    diff[i] = numbs[i] - numbs[i-1]
}
// diff: [1,1,2,1,2,-1]
// [1, 4] + 3
diff[1] += 3 // 从1开始都+3
diff[4 + 1] -= 3 // 5开始恢复之前的差值, 注意不要超出数组长度
```

