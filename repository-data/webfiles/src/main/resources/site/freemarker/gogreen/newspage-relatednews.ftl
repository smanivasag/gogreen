<#include "../include/imports.ftl">
<#if document.relatednews?has_content>
    <#assign containerClass></#assign>
    <#assign containerItemClass></#assign>
    <#if hstRequestContext.cmsRequest>
        <#assign containerDivStart><div class="hst-container"></#assign>
        <#assign containerItemDivStart><div class="hst-item-container"></#assign>
        <#assign containerDivEnd></div></#assign>
    </#if>

    ${containerDivStart}
        ${containerItemDivStart}
            <div class="sidebar-block">
                <h3 class="h3-sidebar-title sidebar-title">Related News</h3>
                <div class="sidebar-content">
                    <ul>
                        <#list document.relatednews as item>
                            <@hst.link var="link" hippobean=item/>
                            <li>
                                <a href="${link}">${item.title?html}</a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </div>
        ${containerDivEnd}
    ${containerDivEnd}
</#if>