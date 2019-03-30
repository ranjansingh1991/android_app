package com.kopykitab.class9.cbse.oswaal.components.adapters;

import com.kopykitab.class9.cbse.oswaal.models.StoreBannerItem;
import com.kopykitab.class9.cbse.oswaal.models.StoreCategoryItem;
import com.kopykitab.class9.cbse.oswaal.models.StoreCategorySection;
import com.kopykitab.class9.cbse.oswaal.models.StoreRecommendationsItem;
import com.kopykitab.class9.cbse.oswaal.models.StoreSearchItem;

public interface StoreItemClickListener {
    void itemClicked(StoreSearchItem search);

    void itemClicked(StoreBannerItem banner);

    void itemClicked(StoreRecommendationsItem recommendation);

    void itemClicked(StoreCategorySection section);

    void itemClicked(StoreCategoryItem item);
}
