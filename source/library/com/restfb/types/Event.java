/*
 * Copyright (c) 2010-2012 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;
import static com.restfb.util.DateUtils.toDateFromShortFormat;

import java.util.Date;

import com.restfb.Facebook;

/**
 * Represents the <a
 * href="http://developers.facebook.com/docs/reference/api/event">Event Graph
 * API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Event extends NamedFacebookType {
  @Facebook
  private NamedFacebookType owner;

  @Facebook
  private String description;

  @Facebook("start_time")
  protected String startTime;

  @Facebook("end_time")
  protected String endTime;

  @Facebook
  private String location;

  @Facebook("rsvp_status")
  private String rsvpStatus;

  @Facebook
  private Venue venue;

  @Facebook
  private String privacy;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private String eid;
  
  private static final long serialVersionUID = 1L;

  public String getId() {
    String id = super.getId();
    if (id != null)
      return id;
    return eid;
  }
  
  public void setId(String val) {
      this.eid = val;
  }

  /**
   * An object containing the name and ID of the user who owns the event
   * 
   * @return An object containing the name and ID of the user who owns the
   *         event.
   */
  public NamedFacebookType getOwner() {
    return owner;
  }
  
  public void setOwner(NamedFacebookType val) {
      this.owner = val;
  }

  /**
   * The long-form HTML description of the event.
   * 
   * @return The long-form HTML description of the event.
   */
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String val) {
      this.description = val;
  }

  /**
   * The start time of the event.
   * 
   * @return The start time of the event.
   */
  public Date getStartTime() {
    Date date = toDateFromLongFormat(startTime);

    // Sometimes the date comes back in short form - if long form parsing
    // failed, try short instead
    return date == null ? toDateFromShortFormat(startTime) : date;
  }
  
  public void setStartTime(Date val) {
      this.startTime = val == null ? null : Long.toString(val.getTime());
  }

  /**
   * The end time of the event.
   * 
   * @return The end time of the event.
   */
  public Date getEndTime() {
    Date date = toDateFromLongFormat(endTime);

    // Sometimes the date comes back in short form - if long form parsing
    // failed, try short instead
    return date == null ? toDateFromShortFormat(endTime) : date;
  }
  
  public void setEndTime(Date val) {
      this.endTime = val == null ? null : Long.toString(val.getTime());
  }

  /**
   * The location for this event, a string name.
   * 
   * @return The location for this event, a string name.
   */
  public String getLocation() {
    return location;
  }
  
  public void setLocation(String val) {
      this.location = val;
  }

  /**
   * The location of this event, a structured address object.
   * 
   * @return The location of this event, a structured address object.
   */
  public Venue getVenue() {
    return venue;
  }
  
  public void setVenue(Venue val) {
      this.venue = val;
  }
  
  public String getLocationId() {
      if (this.venue == null)
          return null;
      
      return this.venue.getId();
  }
  
  public void setLocationId(String val) {
      if (this.venue == null) {
          if (val == null)
             return;
          else
             this.venue = new Venue();
      }
      
      this.venue.setId(val);
  }
  
  public Double getLatitude() {
      return this.venue == null ? null : this.venue.getLatitude();
  }
  
  public void setLatitude(Double val) {
      if (this.venue == null) {
          if (val == null)
              return;
          else
              this.venue = new Venue();
      }
      
      this.venue.setLatitude(val);
  }
  
  public Double getLongitude() {
      return this.venue == null ? null : this.venue.getLongitude();
  }
  
  public void setLongitude(Double val) {
      if (this.venue == null) {
          if (val == null)
              return;
          else
              this.venue = new Venue();
      }
      
      this.venue.setLongitude(val);
  }
  
  /**
   * Sets venue info from the given location Page
   * @param page
   */
  public void setLocation(Location location) {
      
      if (this.venue == null) {
          if (location == null)
              return;
          else
              this.venue = new Venue();
      }
      
      this.venue.setFrom(location);  
  }

  /**
   * The RSVP status of this event.
   * 
   * @return The RSVP status of this event.
   */
  public String getRsvpStatus() {
    return rsvpStatus;
  }
  
  public void setRsvpStatus(String val) {
      this.rsvpStatus = val;
  }

  /**
   * The visibility of this event. Can be 'OPEN', 'CLOSED', or 'SECRET'.
   * 
   * @return The visibility of this event. Can be 'OPEN', 'CLOSED', or 'SECRET'.
   */
  public String getPrivacy() {
    return privacy;
  }
  
  public void setPrivacy(String val) {
      this.privacy = val;
  }

  /**
   * The last time the event was updated.
   * 
   * @return The last time the event was updated.
   */
  public Date getUpdatedTime() {
    return toDateFromLongFormat(updatedTime);
  }
  
  public void setUpdatedTime(Date val) {
      this.updatedTime = Long.toString(val.getTime());
  }

}
