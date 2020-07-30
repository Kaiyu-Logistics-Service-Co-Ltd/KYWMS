package org.example.ec.exception;

/**
 * @auther cssly
 * @create 2020/6/30 21:49
 */
// DBExeption.java
public class DBException {

    public static class BadExecution extends BaseException
    {
        private static final long serialVersionUID = 3555714415375055302L;
        public BadExecution(String msg) {
            super(msg);
        }
    }

    public static class NoData extends BaseException
    {
        private static final long serialVersionUID = 8777415230393628334L;
        public NoData(String msg) {
            super(msg);
        }
    }

    public static class TooMuchData extends BaseException
    {
        private static final long serialVersionUID = -3987707665150073980L;
        public TooMuchData(String msg) {
            super(msg);
        }
    }

    public static class InvalidParam extends BaseException
    {
        private static final long serialVersionUID = 4235225697094262603L;
        public InvalidParam(String msg) {
            super(msg);
        }
    }
}