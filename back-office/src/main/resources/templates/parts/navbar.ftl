<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Tka4vn</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <#if user??>
                <li class="nav-item"><a class="nav-link" href="/request">Request</a></li>
                <li class="nav-item"><a class="nav-link" href="/services">Services</a></li>
                <li class="nav-item"><a class="nav-link" href="/roles">Roles</a></li>
            </#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/user">User list</a></li></#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/ingress">Ingress</a></li></#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/appointment">Appointment</a></li></#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/employee">Employee</a></li></#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/position">Position</a></li></#if>
            <#if isAdmin><li class="nav-item"><a class="nav-link" href="/department">Department</a></li></#if>
        </ul>
        <div class="navbar-text mr-3"><#if user??><u>${name}</u></#if></div>
        <form action="/logout" method="post">
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <button class="btn btn-outline-secondary" type="submit"><#if user??>Выход<#else>Вход</#if></button>
        </form>
    </div>
</nav>