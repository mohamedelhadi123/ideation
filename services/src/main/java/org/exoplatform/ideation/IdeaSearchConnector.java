/*
package org.exoplatform.ideation;



import org.exoplatform.commons.api.search.SearchServiceConnector;
import org.exoplatform.commons.api.search.data.SearchContext;
import org.exoplatform.commons.api.search.data.SearchResult;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.portal.mop.SiteKey;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.ideation.storage.dao.*;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.Util.ListUtil;
import org.exoplatform.ideation.Util.ResourceUtil;
import org.exoplatform.ideation.Util.StringUtil;
import org.exoplatform.ideation.Util.ideaUtil;
import org.exoplatform.ideation.Util.UserUtil;
import org.exoplatform.web.WebAppController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class IdeaSearchConnector extends SearchServiceConnector {

    private static final int MAX_EXCERPT_LENGTH = 430;
    private IdeaService ideaService;
    private WebAppController controller;


    public IdeaSearchConnector(InitParams initParams, IdeaService IdeaService, WebAppController controller) {
        super(initParams);
        this.ideaService = ideaService;
        this.controller = controller;
    }

    @Override
    public Collection<SearchResult> search(SearchContext context,
                                           String query,
                                           Collection<String> sites,
                                           int offset,
                                           int limit,
                                           String sort,
                                           String order) {
        List<SearchResult> result = new ArrayList<SearchResult>();
        if(query == null || query.trim().isEmpty()) {
            return result;
        }

        query = StringUtil.FUZZY.matcher(query.trim()).replaceAll("");

        Identity currentUser = ConversationState.getCurrent().getIdentity();
        List<String> permissions = UserUtil.getMemberships(currentUser);

        ideaEntityQuery ideaEntityQuery = new ideaEntityQuery();
        ideaEntityQuery.setKeyword(query);
        ideaEntityQuery.setAccessible(currentUser);
        OrderBy orderBy = buildOrderBy(sort, order);
        if (orderBy != null) {
            ideaEntityQuery.setOrderBy(Arrays.asList(orderBy));
        }
        SimpleDateFormat  df = new SimpleDateFormat("EEEEE, MMMMMMMM d, yyyy");
        df.setTimeZone(userService.getUserTimezone(currentUser.getUserId()));
        ideaEntity[] ideaEntitys = ListUtil.load(IdeaService.findideaEntitys(ideaEntityQuery), 0, -1);
        for (ideaEntity t : ideaEntitys) {
            result.add(buildResult(t, context, df));
        }

        return ResourceUtil.subList(result, offset, limit);
    }

    private SearchResult buildResult(IdeaEntity t, SearchContext ctx, SimpleDateFormat df) {
        String url = buildUrl(t, ctx);
        String imageUrl = buildImageUrl(t);
        String excerpt = buildExcerpt(t);
        return new ideaEntitySearchResult(url, t.getTitle(), excerpt, imageUrl, t.getCreatedTime().getTime(), 0);
    }

    private String buildExcerpt(IdeaEntity t) {
        String description = t.getDescription();
        if (description != null) {
            if (description.length() > MAX_EXCERPT_LENGTH) {
                return description.substring(0, MAX_EXCERPT_LENGTH) + "...";
            } else {
                return description;
            }
        }

        return null;
    }

    private String buildImageUrl(IdeaEntity t) {
        return null;
    }

    private String buildUrl(IdeaEntity t, SearchContext context) {
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        return ideaEntityUtil.buildideaEntityURL(t, SiteKey.portal(context.getSiteName()), container, controller.getRouter());
    }

    private OrderBy buildOrderBy(String sort, String order) {
        String orderBy = null;
        if (StringUtil.ORDERBY_DATE.equals(sort) || StringUtil.ORDERBY_RELEVANCY.equals(sort)) {
            orderBy = ideaEntityUtil.CREATED_TIME;
        } else if (StringUtil.ORDERBY_TITLE.equals(sort)) {
            orderBy = ideaEntityUtil.TITLE;
        }
        if (orderBy != null) {
            if (StringUtil.ASC.equals(order)) {
                return new OrderBy.ASC(orderBy);
            } else {
                return new OrderBy.DESC(orderBy);
            }
        }
        return null;
    }
}

*/