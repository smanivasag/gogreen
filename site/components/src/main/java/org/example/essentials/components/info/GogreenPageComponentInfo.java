package org.example.essentials.components.info;

import org.hippoecm.hst.core.parameters.Parameter;

/* @FieldGroupList({
    @FieldGroup(
            titleKey = "group.teaser",
            value = { "title", "description" }
    )
}) */
public interface GogreenPageComponentInfo {

    @Parameter(name = "title", required = true, displayName = "Title")
    String getTitle();

    @Parameter(name = "description", required = true, displayName = "Description")
    String getDescription();

}
