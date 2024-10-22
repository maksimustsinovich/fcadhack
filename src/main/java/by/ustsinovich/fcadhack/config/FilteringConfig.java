package by.ustsinovich.fcadhack.config;

import org.springframework.stereotype.Component;

@Component
public class FilteringConfig {

    private FilteringType globalType = FilteringType.MASK;

    public FilteringType getGlobalType() {
        return globalType;
    }

    public void setGlobalType(FilteringType globalType) {
        this.globalType = globalType;
    }
}
