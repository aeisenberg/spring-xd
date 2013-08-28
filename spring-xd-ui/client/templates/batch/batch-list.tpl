<div class="row">
    <div class="span1 offset10">
        <form class="form-search">
            <input type="text" id="job_filter" class="input-small search-query" placeholder="Filter"/>
        </form>
    </div>
</div>
<table class="table table-bordered table-striped info-table" id="batch">
	<thead>
		<tr>
			<td>Job name</td>
			<td>Description</td>
			<td>Execution count</td>
			<td>Actions</td>
		</tr>
	</thead>
	<tbody>
    	<% jobs.forEach(function(job) {
    	   var name = job.attributes.name, details = name + '_details', detailsRow = name + '_detailsRow'; %>
        <tr id="<%= name %>">
    		<td>
    		  <button class="btn detailAction" type="button" job-name="<%= name %>" data-toggle="collapse" data-target="#<%= details %>">
    		    <strong><a><%= name %></a>
    		  </strong></button>
    		</td>
    		<td><%= job.attributes.description %></td>
    		<td><%= job.attributes.executionCount %></td>
    		<td>
    		  <button class="btn btn-mini launchAction <%=job.attributes.launchable ? '' : 'disabled'%>" job-name="<%= name %>" type="button">Launch</button>
    	    </td>
    	</tr>
    	<tr id="<%= detailsRow %>"><td colspan="4"><div id="<%= details %>" class="collapse"></div></td></tr>
        <% }); %>
	</tbody>
</table>
