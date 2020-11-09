# MPAndroidChartPractice
Practice of MPAndroidChart library of Android

## Try 1
### Just mark ten random vertices between 0 and 10
<img src="https://user-images.githubusercontent.com/57310034/98114282-5dce9e00-1ee8-11eb-8879-b7d2125346c8.jpeg" width="30%" height="30%"/>
<b>Improvement point</b> <br>
1. Landscape mode for high visibility <br>
2. But it should not be reconstruct when change orientation. Because Data can be rost. <br>

## Try 2
### switch orientation
1. prevent reconstruct when change orientation
```xml
<activity 
    android:name=".MainActivity"
    android:configChanges="orientation|screenSize">
...
</activity>
```
2. chane orientation method
```
private fun changeScreenMode() {
    requestedOrientation =
        if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    currentOrientation = requestedOrientation
}
```
<img src="https://user-images.githubusercontent.com/57310034/98118706-f2d49580-1eee-11eb-81c9-b873d70d60b8.gif" width="30%" height="30%"/>

## Try 3
#### design like a ECG monitor

1. chart attributes
```
isDragEnabled = false
description = null
setBackgroundColor(getColor(R.color.colorBackground))
setScaleEnabled(false)
setTouchEnabled(false)
```

2. Axis attributes
```
xAxis.run {
    isEnabled = true
    position = XAxis.XAxisPosition.BOTTOM_INSIDE
    axisLineColor = Color.BLACK
    setDrawAxisLine(true)
    setDrawGridLines(false)
}

axisLeft.run {
    isEnabled = true
    textColor = Color.BLACK
    axisLineColor = Color.BLACK
    labelCount = 0
    setDrawGridLines(false)
    setDrawAxisLine(true)
}

axisRight.isEnabled = false
```

3. Data set attributes
```
color = Color.BLACK
label = null
setDrawCircles(false)
setDrawValues(false)
```
<img src="https://user-images.githubusercontent.com/57310034/98527291-3009a080-22be-11eb-8a1c-e51aaa66a69f.png" width="50%" height="50%"/>

