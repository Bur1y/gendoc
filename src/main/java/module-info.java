module com.nrtk.bur1y.gendoc {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.base;
    requires java.base;
    requires java.desktop;
    requires java.naming;
    requires java.net.http;

    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml.schemas;
    requires odfdom.java;
    requires simple.odf;


    opens com.nrtk.bur1y.gendoc to javafx.fxml;
    opens com.nrtk.bur1y.gendoc.Controllers;
    opens com.nrtk.bur1y.gendoc.API.Import;
    opens com.nrtk.bur1y.gendoc.Data;
    opens com.nrtk.bur1y.gendoc.API.Export;

    exports com.nrtk.bur1y.gendoc;
    exports com.nrtk.bur1y.gendoc.Controllers;
    exports com.nrtk.bur1y.gendoc.API.Import;
    exports com.nrtk.bur1y.gendoc.Data;
    exports com.nrtk.bur1y.gendoc.API.Export;
}