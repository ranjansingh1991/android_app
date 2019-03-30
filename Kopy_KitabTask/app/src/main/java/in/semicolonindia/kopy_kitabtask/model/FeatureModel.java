package in.semicolonindia.kopy_kitabtask.model;

/**
 * Created by RANJAN SINGH on 9/18/2018.
 */

@SuppressWarnings("ALL")
public class FeatureModel {
    private int fetureImage;
    private String featureName;

    public FeatureModel(int fetureImage, String featureName) {
        this.fetureImage = fetureImage;
        this.featureName = featureName;
    }

    public int getFetureImage() {
        return fetureImage;
    }

    public String getFeatureName() {
        return featureName;
    }
}
