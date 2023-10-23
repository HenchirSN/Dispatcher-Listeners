enum Filters implements EventFilter {

    ALWAYS_TRUE {
        @Override
        public boolean pass(Object event) {
            return true;
        }
    },
    ALWAYS_FALSE {
        @Override
        public boolean pass(Object event) {
            return false;
        }
    },
    NOT_NULL {
        @Override
        public boolean pass(Object event) {
            return event != null;
        }
    }
}