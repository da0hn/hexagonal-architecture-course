package org.gabriel.desktop.build.production;

// Responsável por configurar os serviços do spring
//@Configuration
//@EnableTransactionManagement
//@ComponentScan({
//        // adptadores front-end javafx
//        "conta.tela",
//        "conta.prd",
//        // core do sistema
//        "conta.sistema",
//        // adptadores hsqdl
//        "conta.servicos.respositorio"})
public class Build4 {

    //Build 4 - Adaptador JavaFX -> Sistema <- Adaptadores Real em Produção
//
    //    @Bean
    //    public DataSource dataSource() {
    //        var ds = new SimpleDriverDataSource();
    //        ds.setDriverClass(org.hsqldb.jdbcDriver.class);
    //        ds.setUrl("jdbc:hsqldb:file:D:/Java/hsql-2.4.1/conta/");
    //        ds.setUsername("sa");
    //        ds.setPassword("1234");
    //        return ds;
    //    }
    //
    //    @Bean
    //    public JdbcTemplate jdbcTemplate() {
    //        return new JdbcTemplate(dataSource());
    //    }
    //
    //    @Bean
    //    public DataSourceTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
