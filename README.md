# ShapeButton
## 自定义ShapeButton，带Ripple效果


### 注意事项：初版开启了Ripple效果，则无法使用边框效果；

|自定义属性|属性描述|
|:---|:---|
|bgDefaultColor|背景默认的颜色|
|bgPressColor|点击按钮的颜色|
|openRipple|是否开启Ripple效果|
|bgRippleColor|Ripple效果的颜色|
|drawRippleRadius|每次绘制Ripple的半径（刷新速率）|
|textColor|默认文字的颜色|
|strokeWidth|边框宽度|
|strokeColor|边框颜色|
|fourCornerRadius|矩形四个圆角的半径|
|leftTopRadius|矩形左上角圆角半径|
|rightTopRadius|矩形右上角圆角半径|
|rightBottomRadius|矩形右下角圆角半径|
|leftBottomRadius|矩形左下角圆角半径|

![截图](https://github.com/fansonq/ShapeButton/blob/master/screenshot/gif.gif)

### XML使用方式
```
<com.fansonq.shapebuttonlib.ShapeButton
        android:layout_width="150dp"
        android:layout_height="50dp"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="18dp"
        android:text="Ripple"
        app:bgDefaultColor="@android:color/holo_green_dark"
        app:bgPressColor="@android:color/holo_blue_bright"
        app:bgRippleColor="@android:color/holo_blue_bright"
        app:fourCornerRadius="30dp"
        app:openRipple="true"
        app:textColor="@android:color/white"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
         />
```

```
<com.fansonq.shapebuttonlib.ShapeButton
        android:layout_width="150dp"
        android:layout_height="50dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="30dp"
        android:text="Stroke"
        app:bgDefaultColor="@android:color/holo_green_dark"
        app:bgPressColor="@android:color/holo_orange_dark"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/sb1"
        app:openRipple="false"
        app:rightBottomRadius="30dp"
        app:rightTopRadius="30dp"
        app:strokeColor="@android:color/holo_orange_dark"
        app:strokeWidth="2dp"
        app:textColor="@android:color/white"
        tools:layout_editor_absoluteY="112dp" />
```

### 引用方式
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
dependencies {
	        implementation 'com.github.fansonq:ShapeButton:1.0.0'
	}
```
