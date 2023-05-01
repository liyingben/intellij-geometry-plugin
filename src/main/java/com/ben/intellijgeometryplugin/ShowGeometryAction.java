package com.ben.intellijgeometryplugin;

import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.actions.XFetchValueActionBase;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jtstest.testbuilder.JTSTestBuilder;
import org.locationtech.jtstest.testbuilder.LayerListPanel;
import org.locationtech.jtstest.testbuilder.controller.JTSTestBuilderController;
import org.locationtech.jtstest.testbuilder.model.Layer;
import org.locationtech.jtstest.testbuilder.model.LayerList;
import org.locationtech.jtstest.testbuilder.model.StaticGeometryContainer;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.List;

public class ShowGeometryAction extends XFetchValueActionBase {


    //绘制图像的颜色
    List<Color> colorList = Arrays.asList(Color.pink, Color.cyan, Color.gray, Color.magenta, Color.orange, Color.yellow);


    @Override
    protected void handle(Project project, String value, XDebuggerTree tree) {
        CopyPasteManager.getInstance().setContents(new StringSelection(value));
        Geometry watchGeomety = null;
        try {
            watchGeomety = new WKTReader().read(value);
        } catch (Exception e) {
            return;
        }
        XValueNodeImpl xValueNode = (XValueNodeImpl) tree.getLastSelectedPathComponent();
        Geometry finalWatchGeometry = watchGeomety;
        ProgressManager.getInstance().run(new Task.Backgroundable(null, "ShowGeometry") {

            public void run(ProgressIndicator progressIndicator) {
                if (JTSTestBuilder.instance() == null) {
                    JTSTestBuilder.main(new String[0]);
                }
                JTSTestBuilder.frame().setVisible(true);
                LayerList layers = JTSTestBuilder.model().getLayersAll();
                Layer lyr = layers.getLayer(0);

                Color color = colorList.get(JTSTestBuilder.model().getLayersBase().size() % colorList.size());
                Layer copy = JTSTestBuilder.model().layerCopy(lyr);
                //复制图层
                String parent = xValueNode.getParent().toString();
                if (parent == null || "".equals(parent)) {
                    copy.setName(xValueNode.getName());
                } else {
                    copy.setName(xValueNode.getParent().toString() + "." + xValueNode.getName());
                }

                copy.getLayerStyle().setVertexColor(color);
                copy.getLayerStyle().setColor(color);
                copy.getLayerStyle().setLabelColor(color);
                copy.getLayerStyle().getGeomStyle().setLineColor(color);
                copy.getLayerStyle().getGeomStyle().setFillColor(color);

                LayerListPanel layerListPanel = JTSTestBuilderController
                        .frame()
                        .getLayerListPanel();
                layerListPanel.populateList();
                layerListPanel.setLayerFocus(
                        layerListPanel.findLayerItem(copy)
                        );
                StaticGeometryContainer src = (StaticGeometryContainer) copy.getSource();
                src.setGeometry(finalWatchGeometry);
                JTSTestBuilderController.frame().geometryChanged();
                JTSTestBuilder.controller().geometryViewChanged();
            }
        });

    }
}
