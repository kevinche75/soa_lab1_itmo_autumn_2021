package ru.itmo.servlet;

import ru.itmo.service.LabWorksService;
import ru.itmo.utils.FieldConverter;
import ru.itmo.utils.LabWorkParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "labworks", value = "/labworks*")
public class LabWorkServlet extends HttpServlet {

    private static final String NAME_PARAM = "name";
    private static final String CREATION_DATE_PARAM = "creationDate";
    private static final String MINIMAL_POINT_PARAM = "minimalPoint";
    private static final String MAXIMAL_POINT_PARAM = "maximalPoint";
    private static final String PERSONAL_QUALITIES_MAXIMUM_PARAM = "personalQualitiesMaximum";
    private static final String DIFFICULTY_PARAM = "difficulty";
    private static final String COORDINATES_X_PARAM = "coordinatesX";
    private static final String COORDINATES_Y_PARAM = "coordinatesY";

    private static final String PAGE_IDX_PARAM = "pageIdx";
    private static final String PAGE_SIZE_PARAM = "pageSize";
    private static final String SORT_FIELD_PARAM = "sortField";

    private static final String MINIMAL_NAME_FLAG = "minName";
    private static final String COUNT_PERSONAL_QUALITIES_MAXIMUM_FLAG = "count";
    private static final String LESS_MAXIMUM_POINT_FLAG = "lessMaximumPoint";

    private LabWorksService service;

    private LabWorkParams getLabWorksParams(HttpServletRequest request){
        return new LabWorkParams(
                request.getParameter(NAME_PARAM),
                request.getParameter(CREATION_DATE_PARAM),
                request.getParameter(MINIMAL_POINT_PARAM),
                request.getParameter(MAXIMAL_POINT_PARAM),
                request.getParameter(PERSONAL_QUALITIES_MAXIMUM_PARAM),
                request.getParameter(DIFFICULTY_PARAM),
                request.getParameter(COORDINATES_X_PARAM),
                request.getParameter(COORDINATES_Y_PARAM),
                request.getParameter(PAGE_IDX_PARAM),
                request.getParameter(PAGE_SIZE_PARAM),
                request.getParameter(SORT_FIELD_PARAM)
        );
    }

    @Override
    public void init() throws ServletException {
        super.init();
        service = new LabWorksService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        String pathInfo = req.getPathInfo();
        if (pathInfo == null){
            Boolean minNameFlag = FieldConverter.booleanConverter(req.getParameter(MINIMAL_NAME_FLAG));
            if (minNameFlag != null && minNameFlag){
                service.getMinName();
                return;
            }
            Boolean count = FieldConverter.booleanConverter(req.getParameter(COUNT_PERSONAL_QUALITIES_MAXIMUM_FLAG));
            if (count != null && count){
                service.countPersonalQualitiesMaximum(FieldConverter.longConvert(req.getParameter(PERSONAL_QUALITIES_MAXIMUM_PARAM)));
                return;
            }
            LabWorkParams filterParams = getLabWorksParams(req);
            Boolean lessMaximumPoint = FieldConverter.booleanConverter(req.getParameter(LESS_MAXIMUM_POINT_FLAG));
            if (lessMaximumPoint != null && lessMaximumPoint){
                service.getLessMaximumPoint(filterParams);
                return;
            }
            service.getAllLabWorks(filterParams, resp);
        } else {
            String[] parts = pathInfo.split("/");
            if (parts.length > 1){
                service.getError(resp);
                return;
            }
            if (FieldConverter.intConverter(parts[0]) != null){
                service.getLabWork(FieldConverter.intConverter(parts[0]));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
