/*
 * Copyright (c) Team 7, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package com.team7.cmput301.android.theirisproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.team7.cmput301.android.theirisproject.model.GeoLocation;
import com.team7.cmput301.android.theirisproject.model.Record;
import com.team7.cmput301.android.theirisproject.model.RecordPhoto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;

public class RecordTest {

    private String user = "care provider";
    String problemID = "mwmwmw";
    String title = "Title";
    String desc = "Text";
    private Date date;
    private GeoLocation geoLocation = new GeoLocation(1.0,1.0);
    private List<RecordPhoto> recordPhotos = new ArrayList<>();

    private String blob;
    private int x;
    private int y;

    @Test
    public void testRecord(){
        // Test the creation of record

        Record record = getTestRecord();

        Assert.assertEquals(user, record.getUser());
        Assert.assertEquals(problemID, record.getProblemId());
        Assert.assertEquals(title, record.getTitle());
        Assert.assertEquals(desc, record.getDesc());
        Assert.assertEquals(geoLocation.asDouble().length, record.getGeoLocation().asDouble().length);
        Assert.assertEquals(recordPhotos.size(), record.getRecordPhotos().size());
    }

    @Test
    public void testAddPhoto() {
        // Test adding a photo to record

        Record record = getTestRecord();
        RecordPhoto recordPhoto = getTestRecordPhoto();

        record.addRecordPhoto(recordPhoto);
        Assert.assertEquals(record.getRecordPhotos().size(), 1);
        Assert.assertEquals(record.getRecordPhotos().get(0), recordPhoto);
    }

    @Test
    public void testDeletePhoto() {
        // Test deleting a record photo

        Record record = getTestRecord();
        RecordPhoto recordPhoto = getTestRecordPhoto();

        record.addRecordPhoto(recordPhoto);
        Assert.assertEquals(record.getRecordPhotos().size(), 1);
        Assert.assertEquals(record.getRecordPhotos().get(0), recordPhoto);
        record.deleteRecordPhoto(recordPhoto);
        Assert.assertEquals(record.getRecordPhotos().size(), 0);
    }

    @Test
    public void testEditGeoLocation() {
        // Test editing geolocation

        Record record = getTestRecord();

        GeoLocation updatedGeoLocation = new GeoLocation(1.0, 1.0);
        Assert.assertEquals(updatedGeoLocation.asDouble().length, record.getGeoLocation().asDouble().length);
    }

    @Test
    public void testEditRecordPhotos() {
        // Test editing record photos

        Record record = getTestRecord();
        List<RecordPhoto> recordPhotos = new ArrayList<>();

        Assert.assertEquals(recordPhotos, record.getRecordPhotos());
    }

    @Test
    public void testIncorrectTitle() {
        // Test catching exception of invalid record title

        try {
            String user = "care provider";
            String problemId = "mwmwmw";
            String title = "over30characterslong1234567890over30characterslong1234567890over30characterslong1234567890";
            String text = "Text";
            Date date = new Date();
            GeoLocation geoLocation = new GeoLocation(1.0, 1.0);
            List<RecordPhoto> recordPhotos = new ArrayList<>();

            Record record = new Record(user, problemId, title, text, date, geoLocation, recordPhotos);
            if (title.length() > 30) throw new Exception();
            fail("Should throw an exception if title length exceeds 30 characters");
        } catch (Exception e) {
            assert(true);
        }
    }

    @Test
    public void testIncorrectDesc() {
        // Test catching exception of invalid description

        try {
            String user = "care provider";
            String problemId = "mwmwmw";
            String title = "Title";
            String text = "over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789over300characterslong123456789";
            Date date = new Date();
            GeoLocation geoLocation = new GeoLocation(1.0, 1.0);
            List<RecordPhoto> recordPhotos = new ArrayList<>();

            Record record = new Record(user, problemId, title, text, date, geoLocation, recordPhotos);
            if (text.length() > 300) throw new Exception();
            fail("Should throw an exception if description length exceeds 300 characters");
        } catch (Exception e) {
            assert(true);
        }
    }

    @Test
    public void testIncorrectGeoLocation() {
        // Test catching exception of invalid geo location

        try {
            String user = "care provider";
            String problemId = "mwmwmw";
            String title = "Title";
            String text = "Text";
            Date date = new Date();
            GeoLocation geoLocation = new GeoLocation(100.0, 200.0);
            List<RecordPhoto> recordPhotos = new ArrayList<>();
            Record record = new Record(user, problemId, title, text, date, geoLocation, recordPhotos);

            if (geoLocation.asDouble()[0] > 90) throw new Exception();
            fail("Should throw an exception if geolocation coordinates are out of bounds");
        } catch (Exception e) {
            assert(true);
        }
    }

    private Record getTestRecord() {

        String user = "care provider";
        String problemId = "mwmwmw";
        String title = "Title";
        String text = "Text";
        Date date = new Date();
        GeoLocation geoLocation = new GeoLocation(1.0, 1.0);
        List<RecordPhoto> recordPhotos = new ArrayList<>();

        return new Record(user, problemId, title, text, date, geoLocation, recordPhotos);
    }


    private RecordPhoto getTestRecordPhoto() {
        String recordId = "1234xcas234";
        return new RecordPhoto(recordId, Bitmap.createBitmap(256,256,Bitmap.Config.ARGB_8888));
    }

}