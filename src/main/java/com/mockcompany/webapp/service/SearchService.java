package com.mockcompany.webapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mockcompany.webapp.model.ProductItem;

@Service
public class SearchService {

    public SearchService() {
    }

    public Collection<ProductItem> searchMatches(Iterable<ProductItem> allItems, String query) {
        List<ProductItem> matches = new ArrayList<>();
        for (ProductItem item : allItems) {
            boolean matchesSearch = false;
            boolean hasQuotes = false;
            String name = item.getName();
            String description = item.getDescription();

            if (query.charAt(0) == '"' && query.charAt(query.length() - 1) == '"' )
                hasQuotes = true;

            if (hasQuotes && (name.equals(query.substring(1,query.length() - 1)) || description.equals(query.substring(1,query.length() - 1))))
                matchesSearch = true;
            if(name.toLowerCase().contains(query.toLowerCase()) || description.toLowerCase().contains(query.toLowerCase()))
                matchesSearch = true;

            if (matchesSearch)
                matches.add(item);
        }
        return matches;
    }

}
