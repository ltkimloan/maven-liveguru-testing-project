package pageUIs.admin;

public class AdminInvoicesUIs {
    public static final String INVOICE_DISPLAYED_BY_SORT = "xpath=//span[contains(.,'Invoice')]/parent::a[@name='increment_id' and @class='sort-arrow-%s']";
    public static final String INVOICE_SORT = "name=increment_id";
    public static final String INVOICE_VALUE_BY_RECORD_NUMBER = "xpath=//table[@id='sales_invoice_grid_table']//tr[contains(@title,'')][%s]//td[2]";
}
