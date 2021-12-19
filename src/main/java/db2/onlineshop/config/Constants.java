package db2.onlineshop.config;

public final class Constants {
    private Constants() {}

    public static class jpaCommon {
        public static final String REPO_PACKAGE_PATH = "db2.onlineshop.dao.common";
        public static final String ENTITY_PACKAGE_PATH = "db2.onlineshop.entity.common";
        private jpaCommon() {}
    }

    public static class jpaMain {
        public static final String REPO_PACKAGE_PATH = "db2.onlineshop.dao.main";
        public static final String ENTITY_PACKAGE_PATH = "db2.onlineshop.entity.main";
        private jpaMain() {}
    }

}
