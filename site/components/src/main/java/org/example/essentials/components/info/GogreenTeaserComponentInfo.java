package org.example.essentials.components.info;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.JcrPath;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
    @FieldGroup(
            titleKey = "group.teaser",
            value = { "title", "image", "summary", "ctaLabel", "ctaExternalLink" }
    )
})
public interface GogreenTeaserComponentInfo {

    @Parameter(name = "title", required = true, displayName = "Title")
    String getTitle();

    @Parameter(name = "image", displayName = "Image")
    @JcrPath(isRelative = true, pickerInitialPath = "samples", pickerSelectableNodeTypes = "hippogallery:imageset")
    String getImage();

    @Parameter(name = "summary", required = true, displayName = "Summary", defaultValue = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea, omnis qui veritatis aspernatur officiis adipisci velit pariatur, libero vero perferendis earum. Optio quod fuga amet quasi quia dignissimos assumenda aspernatur!")
    String getSummary();

    @Parameter(name = "ctaLabel", required = true, displayName = "CTA Label", defaultValue = "Read more")
    String getCTAText();

    @Parameter(name = "ctaExternalLink", required = true, displayName = "CTA External Link")
    String getCTAExternalLink();

}
