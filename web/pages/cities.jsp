<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:default-layout>
    <jsp:attribute name="title">
      Cities
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:if test="${not empty errorCodeValue}">
            <div class="alert alert-danger" role="alert">
                You request finished with some error. Reason: ${errorCodeValue}.
            </div>
        </c:if>

        <p>Number of page view: ${visitCount}</p>
        <p>Selected cities ids: ${selectedCitiesString}</p>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#create-city-modal">
            Create new city
        </button>

        <div class="modal fade" id="create-city-modal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Create new city entry</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="create-city-form" action="/cities/?redirect" method="post">
                            <div class="form-group">
                                <label for="city-name">Name</label>
                                <input type="text" class="form-control" name="city-name" id="city-name"
                                       placeholder="City name">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" form="create-city-form">Create</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="update-city-modal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Update city data</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="update-city-form" action="/cities/?redirect" method="post">
                            <input type="hidden" name="_method" value="put"/>
                            <input type="hidden" name="city_id"/>

                            <div class="form-group">
                                <label for="update-city-form__name">Name</label>
                                <input type="text" class="form-control" name="city-name" id="update-city-form__name"
                                       placeholder="City name">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-success" form="update-city-form">Update</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="delete-city-modal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Are u sure?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="delete-city-form" action="/cities/?redirect" method="post" class="d-none">
                            <input type="hidden" name="_method" value="delete"/>
                            <input type="hidden" name="city_id"/>
                        </form>
                        Delete city: <b class="city__name"></b>[id=<span class="city__id"></span>]?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger" form="delete-city-form">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="my-3">
            <div class="table-responsive">
                <table class="table city-list">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="city" items="${cities}">
                        <tr>
                            <td class="align-middle">
                                <c:set var="selected" value="false"/>
                                <c:forEach var="selectedId" items="${selectedCitiesIds}">
                                    <c:if test="${selectedId == city.id}">
                                        <c:set var="selected" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${selected}">
                                        <input id="check-${city.id}" type="checkbox" data-city-id="${city.id}" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input id="check-${city.id}" type="checkbox" data-city-id="${city.id}">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <th class="align-middle"><label for="check-${city.id}">${city.id}</label></th>
                            <td class="align-middle">${city.name}</td>
                            <td class="align-middle">
                                <button type="button"
                                        class="btn btn-info btn-sm"
                                        data-toggle="modal"
                                        data-target="#update-city-modal"
                                        data-city_id="${city.id}"
                                        data-city_name="${city.name}">
                                    Edit
                                </button>
                                <button type="button"
                                        class="btn btn-danger btn-sm"
                                        data-toggle="modal"
                                        data-target="#delete-city-modal"
                                        data-city_id="${city.id}"
                                        data-city_name="${city.name}">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <form class="form-inline my-3" method="GET">
            <label class="sr-only" for="city_id">City id</label>
            <input type="text" value="${idRepr}" name="city_id" class="form-control mb-2 mr-sm-2" id="city_id"
                   placeholder="City id">
            <label class="sr-only" for="name">Name</label>
            <input type="text" value="${nameValue}" name="name" class="form-control mb-2 mr-sm-2" id="name"
                   placeholder="City name">
            <label class="my-1 mr-2" for="order-by">Order by</label>
            <select class="custom-select my-1 mr-sm-2" name="sort" id="order-by">
                <option value="${sortValue}" selected disabled>${sortValue}</option>
                <option value="asc">ascending</option>
                <option value="desc">descending</option>
            </select>
            <button type="submit" class="btn btn-primary mb-2">Filter</button>
        </form>

    </jsp:attribute>
</t:default-layout>
