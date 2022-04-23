package whitehole.smg.worldmapObject;

import whitehole.rendering.BmdRenderer;
import whitehole.rendering.GLRenderer;
import whitehole.smg.Bcsv;
import com.jogamp.opengl.GL2;
import whitehole.rendering.RendererFactory;

public class GalaxyPreview extends WorldmapPoint {
    public GalaxyPreview(Bcsv.Entry entry, WorldmapPoint base) {
        super(base.entry);
        entryGP = entry;
    }
    
    public GalaxyPreview(Bcsv.Entry entry, Bcsv.Entry pointEntry) {
        super(pointEntry);
        entryGP = entry;
    }
    
    @Override
    public void initRenderer(GLRenderer.RenderInfo info) {
        modelRenderer = RendererFactory.tryCreateBmdRenderer(info,(String)entryGP.get("MiniatureName"));
        super.initRenderer(info);
    }
    
    @Override
    public void closeRenderer(GLRenderer.RenderInfo info) {
        if (modelRenderer == null)
            return;
        
        modelRenderer = null;
        super.closeRenderer(info);
    }
    
    @Override
    public void render(GLRenderer.RenderInfo info, GLRenderer pointRenderer) {
        GL2 gl = info.drawable.getGL().getGL2();
        
        gl.glPushMatrix();
        gl.glTranslatef((float)entry.get(-726582764), (float)entry.get(-726582763), (float)entry.get(-726582762));
        if(pointRenderer == null)
            pointRenderer = RendererFactory.tryCreateBmdRenderer(info, "MiniRoutePoint");
        pointRenderer.render(info);
        gl.glTranslatef((float)entryGP.get(1370777937), (float)entryGP.get(1370777938), (float)entryGP.get(1370777939));
        gl.glScalef((float)entryGP.get(-827224888), (float)entryGP.get(-827224888), (float)entryGP.get(-827224888));
        if(entryGP.get(-454084808).equals("BossGalaxyLv3"))
            gl.glRotatef(-45f, 0f, 1f, 0f);
        
        modelRenderer.render(info);
        gl.glPopMatrix();
    }
    
    @Override
    public String getName(){
        return "["+entry.get(70793394)+"] " + entryGP.get("StageName");
    }
    
    GLRenderer modelRenderer;
    public Bcsv.Entry entryGP;
}