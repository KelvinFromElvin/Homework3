package classes;

public class District {
    // when adding new district update isValidDistrict method
    public static final int NEGEV = 1;
    public static final int SOUTH = 2;
    public static final int CENTER = 3;
    public static final int SHARON = 4;
    public static final int NORTH = 5;

    private static final int DEFAULT_DISTRICT = District.CENTER;

    public static boolean isValidDistrict(int district) {
        // O(1)
        switch (district) {
            case NEGEV:
            case SOUTH:
            case CENTER:
            case SHARON:
            case NORTH:
                return true;
            default:
                return false;
        }
    }

    private int district;

    public District() {
        this.district = DEFAULT_DISTRICT;
    }

    public District(int district) {
        this.district = DEFAULT_DISTRICT;

        this.setDistrict(district);
    }

    public int getDistrict() {
        // O(1)
        return this.district;
    }

    public void setDistrict(int district) {
        // O(1)
        if (District.isValidDistrict(district)) {
            this.district = district;
        }
    }

    public String getDistrictName() {
        // O(1)
        switch (this.district) {
            case NEGEV:
                return "Negev";
            case SOUTH:
                return "South";
            case SHARON:
                return "Sharon";
            case NORTH:
                return "North";
            case CENTER:
            default:
                return "Center";
        }
    }
}
