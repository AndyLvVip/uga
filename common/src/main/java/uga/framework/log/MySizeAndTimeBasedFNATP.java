package uga.framework.log;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

import java.io.File;

@NoAutoStart
public class MySizeAndTimeBasedFNATP<E> extends SizeAndTimeBasedFNATP<E> {

    private SizeBasedTriggeringPolicy<E> delegate;

    public MySizeAndTimeBasedFNATP() {
        delegate = new SizeBasedTriggeringPolicy<>();
    }

    public MySizeAndTimeBasedFNATP(SizeBasedTriggeringPolicy<E> delegate) {
        this.delegate = delegate;
    }


    @Override
    public boolean isTriggeringEvent(File activeFile, E event) {
        super.isTriggeringEvent(activeFile, event);
        return delegate.isTriggeringEvent(activeFile, event);
    }
}
