package com.baidu.test.picassodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView myimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimg = (ImageView) findViewById(R.id.myimg);
        //设置缓存指示器（不同颜色代表不同缓存）
        Picasso.with(this).setIndicatorsEnabled(true);
        //设置logger打印工具
        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this)
                .load("http://img1.imgtn.bdimg.com/it/u=391390601,3860916026&fm=11&gp=0.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                //.resize(200,200)
                //只有原图片大于resize设置的大小否则resize无效
                //.onlyScaleDown()
                //居中裁剪（可能会显示不全）
                .centerCrop()
                //让图片填满控件,如果图片尺寸小于View尺寸的话，是不能充满View边界的
                //.centerInside()
                //自动计算大小放入控件中（控件不能用自适应）
                .fit()
                //旋转图片
                .rotate(180,200,100)
                .priority(Picasso.Priority.HIGH)
                .tag("nihao")
                //同步请求
                //.get()
                //异步请求
                //.fetch()
                //不检查内存缓存NO_CACHE  ,NO_STORE不往内存缓存里存
                //.memoryPolicy(MemoryPolicy.NO_CACHE)
                //强制从缓存中读取
                .networkPolicy(NetworkPolicy.OFFLINE)
                .transform(new CropCircleTransformation())
                .into(myimg);
        Picasso.Builder builder=new Picasso.Builder(this);
        //默认线程3
        builder.executor(Executors.newFixedThreadPool(6));
        //设置缓存大小
        builder.memoryCache(new LruCache(5*1024*1024));
    }
}
