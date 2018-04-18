package uga.framework.db.jooq.log;

import org.jooq.ExecuteContext;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.LoggerListener;

/**
 * @Author: andy.lv
 * @Date: created on 2018/1/6
 * @Description:
 */
public class JooqLoggerListener extends LoggerListener {

    private static final JooqLogger LOG   = JooqLogger.getLogger(JooqLoggerListener.class);

    @Override
    public void resultEnd(ExecuteContext ctx) {
        if(LOG.isDebugEnabled())
            LOG.debug("Fetched result", "Total fetched row(s): " + ctx.result().size());
    }
}
