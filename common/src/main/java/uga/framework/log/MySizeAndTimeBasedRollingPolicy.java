package uga.framework.log;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;

public class MySizeAndTimeBasedRollingPolicy<E> extends TimeBasedRollingPolicy<E> {

    FileSize maxFileSize;

    @Override
    public void start() {
        SizeBasedTriggeringPolicy<E> delegate = new SizeBasedTriggeringPolicy<>();
        if(maxFileSize == null) {
            addError("maxFileSize property is mandatory.");
            return;
        } else {
            addInfo("Archive files will be limited to ["+maxFileSize+"] each.");
        }

        delegate.setMaxFileSize(maxFileSize);
        MySizeAndTimeBasedFNATP<E> sizeAndTimeBasedFNATP = new MySizeAndTimeBasedFNATP<E>(delegate);
        sizeAndTimeBasedFNATP.setMaxFileSize(maxFileSize);
        setTimeBasedFileNamingAndTriggeringPolicy(sizeAndTimeBasedFNATP);

        if(!isUnboundedTotalSizeCap() && totalSizeCap.getSize() < maxFileSize.getSize()) {
            addError("totalSizeCap of ["+totalSizeCap+"] is smaller than maxFileSize ["+maxFileSize+"] which is non-sensical");
            return;
        }

        // most work is done by the parent
        super.start();
    }


    public void setMaxFileSize(FileSize aMaxFileSize) {
        this.maxFileSize = aMaxFileSize;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"@"+this.hashCode();
    }

}
