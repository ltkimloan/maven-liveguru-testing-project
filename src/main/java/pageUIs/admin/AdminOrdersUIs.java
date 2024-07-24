package pageUIs.admin;

public class AdminOrdersUIs {
    public static final String STATUS_DROPDOWN = "xpath=//select[@id='sales_order_grid_filter_status']";
    public static final String FIRST_ORDER_CHECKBOX = "xpath=//tr[1]//input[@type='checkbox'][1]";
    public static final String SEARCH_BUTTON = "xpath=//button[@title='Search']";
    public static final String ACTIONS_DROPDOWN = "xpath=//label[text()='Actions']/following-sibling::select[@id='sales_order_grid_massaction-select']";
    public static final String SUBMIT_BUTTON = "xpath=//span[text()='Submit']/ancestor::button";
    public static final String UNSELECT_VISIBLE_BUTTON = "xpath=//a[text()='Unselect Visible']" ;
    public static final String SELECT_VISIBLE_BUTTON = "xpath=//a[text()='Select Visible']";
    public static final String SELECT_ITEM_NUMBER = "id=sales_order_grid_massaction-count";
    public static final String TOTAL_RECORD_FOUND = "XPATH=//td[@class='pager']";
    public static final String LIST_RECORD_ORDER_ID = "xpath=//table[@id='sales_order_grid_table']//tr[contains(@title,'')]//td[2]";
    public static final String VIEW_PAGE_DROPDOWN = "xpath=//select[@name='limit']";
}
