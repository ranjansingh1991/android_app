package com.kopykitab.class9.cbse.oswaal.components.adapters;

import com.kopykitab.class9.cbse.oswaal.models.StoreCategorySection;

/**
 * interface to listen changes in state of sections
 */
public interface StoreCategorySectionListener {
    void onSectionStateChanged(StoreCategorySection section, boolean isOpen);
}