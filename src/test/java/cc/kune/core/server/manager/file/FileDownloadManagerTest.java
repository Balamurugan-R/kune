/*
 *
 * Copyright (C) 2007-2011 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cc.kune.core.server.manager.file;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.ourproject.kune.platf.integration.IntegrationTestHelper;

import cc.kune.core.client.services.ImageSize;
import cc.kune.core.server.manager.file.FileDownloadManager;
import cc.kune.core.server.manager.file.FileUtils;
import cc.kune.core.server.properties.KuneProperties;
import cc.kune.core.shared.domain.utils.StateToken;
import cc.kune.domain.BasicMimeType;
import cc.kune.domain.Content;

import com.google.inject.Inject;

public class FileDownloadManagerTest {

    private static final String SOMETITLE = "Sometitle";

    @Inject
    FileDownloadManager fileDownloadManager;

    @Inject
    KuneProperties kuneProperties;

    private HttpServletResponse resp;
    private Content content;

    private String uploadLocation;

    private FileUtils fileUtils;

    private StateToken stateToken;

    private String filename;

    @Before
    public void create() throws IOException {
        new IntegrationTestHelper(this);
        content = Mockito.mock(Content.class);
        resp = Mockito.mock(HttpServletResponse.class);
        ServletOutputStream oStream = Mockito.mock(ServletOutputStream.class);

        Mockito.when(resp.getOutputStream()).thenReturn(oStream);
        uploadLocation = kuneProperties.get(KuneProperties.UPLOAD_LOCATION);

        fileUtils = Mockito.mock(FileUtils.class);
        Mockito.when(fileUtils.exist(Mockito.anyString())).thenReturn(true);
        stateToken = new StateToken("test.test.1.1");
        filename = "somefile";

        Mockito.when(content.getTitle()).thenReturn(SOMETITLE);
    }

    @Ignore
    public void fileWithNoExtensionDownloadTest() throws Exception {
        throw new Exception("TODO");
    }

    @Test
    public void testJpgThumbDownload() throws IOException {
        String extension = ".jpg";
        contentwhen(new BasicMimeType("image", "jpg"), filename, extension);
        String subExt = ImageSize.thumb.toString();
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "false", subExt, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + "." + subExt + extension, absFile);
        Mockito.verify(resp).setContentType("image/jpg");
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + extension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPdfDownload1() throws IOException {
        String extension = ".pdf";
        contentwhen(new BasicMimeType("application", "pdf"), filename, extension);
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "true", ImageSize.ico.toString(), resp,
                fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + extension, absFile);
        Mockito.verify(resp).setContentType(FileDownloadManager.APPLICATION_X_DOWNLOAD);
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + extension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPdfDownload2() throws IOException {
        String pngExtension = ".png";
        contentwhen(new BasicMimeType("application", "pdf"), filename, ".pdf");
        String subExt = ImageSize.ico.toString();
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "false", subExt, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + "." + subExt + pngExtension, absFile);
        Mockito.verify(resp).setContentType("image/png");
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + pngExtension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPdfDownload3() throws IOException {
        String pngExtension = ".png";
        contentwhen(new BasicMimeType("application", "pdf"), filename, ".pdf");
        String subExt = ImageSize.sized.toString();
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "false", subExt, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + "." + subExt + pngExtension, absFile);
        Mockito.verify(resp).setContentType("image/png");
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + pngExtension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPdfDownload4() throws IOException {
        String pngExtension = ".png";
        contentwhen(new BasicMimeType("application", "pdf"), filename, ".pdf");
        String subExt = ImageSize.thumb.toString();
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "false", subExt, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + "." + subExt + pngExtension, absFile);
        Mockito.verify(resp).setContentType("image/png");
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + pngExtension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPdfDownloadNullMime() throws IOException {
        String extension = ".pdf";
        contentwhen(null, filename, extension);
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "true", ImageSize.ico.toString(), resp,
                fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + extension, absFile);
        Mockito.verify(resp).setContentType(FileDownloadManager.APPLICATION_X_DOWNLOAD);
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + extension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPngDownload1() throws IOException {
        String extension = ".png";
        contentwhen(new BasicMimeType("image", "png"), filename, extension);
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "true", null, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + extension, absFile);
        Mockito.verify(resp).setContentType(FileDownloadManager.APPLICATION_X_DOWNLOAD);
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + extension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    @Test
    public void testPngDownload2() throws IOException {
        String extension = ".png";
        contentwhen(new BasicMimeType("image", "png"), filename, extension);
        String subExt = ImageSize.thumb.toString();
        String absFile = fileDownloadManager.buildResponse(content, stateToken, "false", subExt, resp, fileUtils);
        assertEquals(uploadLocation + FileUtils.toDir(stateToken) + filename + "." + subExt + extension, absFile);
        Mockito.verify(resp).setContentType("image/png");
        Mockito.verify(resp).setHeader(
                FileDownloadManager.RESP_HEADER_CONTEND_DISP,
                FileDownloadManager.RESP_HEADER_ATTACHMENT_FILENAME + SOMETITLE + extension
                        + FileDownloadManager.RESP_HEADER_END);
    }

    private void contentwhen(BasicMimeType mimeType, String filename, String extension) {
        Mockito.when(content.getMimeType()).thenReturn(mimeType);
        Mockito.when(content.getFilename()).thenReturn(filename + extension);
    }
}