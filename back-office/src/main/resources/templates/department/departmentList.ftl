<div class="card-columns">
    <#list departments?sort_by("name") as dep>
        <div class="card my-3">
            <div class="m-2">
                <a href="/department/${dep.id}"><span>${dep.name}</span></a><br/>
            </div>
        </div>
    <#else>
        Нет департаментов
    </#list>
</div>