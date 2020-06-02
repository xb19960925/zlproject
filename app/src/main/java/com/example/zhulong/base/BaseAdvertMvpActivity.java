package com.example.zhulong.base;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.Device;
import com.example.frame.FrameApplication;
import com.example.frame.secret.SystemUtils;
import com.example.zhulong.R;
import com.yiyatech.utils.NetworkUtils;

import butterknife.BindView;


public abstract class BaseAdvertMvpActivity extends BaseMvpActivity {
    @BindView(R.id.advert_image)
    public ImageView advertImage;
    @BindView(R.id.tv_skip)
    public TextView tvSkip;

    @Override
    protected int setLayout() {
        return R.layout.activity_advertisement;
    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }


}
