<#include "../../include/imports.ftl">
cparam -> ${cparam}

<#if cparam??>
  <div>
    <h1>${cparam.title?html}</h1>
    imageBean -> ${imageBean!"hello"}
    <#--  <@hst.link var="link" path="${imageBean}"/>  -->
    <#--  <img src="${link}">  -->
    <#--  <img src="${link}">  -->
    <p>${cparam.summary}</p>
    <a href="${cparam.CTAExternalLink}" target="_blank">${cparam.CTAText?html}</a>
  </div>
</#if>
