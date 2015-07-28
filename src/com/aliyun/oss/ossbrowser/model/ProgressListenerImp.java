// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressListenerImp.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.ProgressEvent;
import com.aliyun.aos.services.oss.model.ProgressListener;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import java.util.Date;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconTableModel

public class ProgressListenerImp
    implements ProgressListener
{

    public void progressChanged(ProgressEvent progressEvent)
    {
        int ratio = -1;
        if(totalSize > 0L)
            ratio = (int)((curSize * 100L) / totalSize);
        switch(progressEvent.getEventCode())
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            break;

        case 0: // '\0'
            int speed = -1;
            long curTime = (new Date()).getTime();
            curSize += progressEvent.getBytesTransfered();
            newUploadSize += progressEvent.getBytesTransfered();
            if(curTime - lastTime > 300L)
            {
                speed = (int)((newUploadSize * 1000L) / (curTime - lastTime));
                lastTime = curTime;
                newUploadSize = 0L;
            }
            if(!isFailed)
                iconTableModel.setValueAt(taskName, ratio, taskType, speed);
            break;

        case 1: // '\001'
            iconTableModel.setValueAt(taskName, 0, taskType, 0L);
            lastTime = (new Date()).getTime();
            break;

        case 2: // '\002'
            iconTableModel.setValueAt(taskName, 100, "Completed", 0L);
            iconTableModel.removeTask(taskName);
            break;

        case 8: // '\b'
            iconTableModel.setValueAt(taskName, ratio, "Canceled", 0L);
            break;

        case 4: // '\004'
            isFailed = true;
            iconTableModel.setValueAt(taskName, ratio, "Failed", 0L);
            break;
        }
    }

    public ProgressListenerImp(ResourceManager resourceManager, IconTableModel iconTableModel, String taskName, long totalSize, int taskType)
    {
        this.totalSize = 0L;
        curSize = 0L;
        newUploadSize = 0L;
        lastTime = 0L;
        isFailed = false;
        this.iconTableModel = iconTableModel;
        this.taskName = taskName;
        this.totalSize = totalSize;
        if(taskType == 1 || taskType == 0)
            this.taskType = "Uploading";
        else
            this.taskType = "Downloading";
    }

    public void setCurSize(long curSize)
    {
        this.curSize = curSize;
    }

    private final int SPEED_UPADATE_INTERVAL = 300;
    private IconTableModel iconTableModel;
    private String taskName;
    private long totalSize;
    private long curSize;
    private long newUploadSize;
    private long lastTime;
    private String taskType;
    private boolean isFailed;
}
