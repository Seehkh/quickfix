/* ====================================================================
 * The QuickFIX Software License, Version 1.0
 *
 * Copyright (c) 2001 ThoughtWorks, Inc.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        ThoughtWorks, Inc. (http://www.thoughtworks.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "QuickFIX" and "ThoughtWorks, Inc." must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact quickfix-users@lists.sourceforge.net.
 *
 * 5. Products derived from this software may not be called "QuickFIX",
 *    nor may "QuickFIX" appear in their name, without prior written
 *    permission of ThoughtWorks, Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THOUGHTWORKS INC OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package quickfix;
import java.util.Date;

public class Message extends FieldMap {
    private int cppPointer;

    protected Header header;
    protected Trailer trailer;

    public Message() {
        create();
        header = new Header();
        trailer = new Trailer();
    }

    public Message(String string) throws InvalidMessage {
        initFromString(string, true);
    }

    public Message(String string, boolean validate) throws InvalidMessage {
        initFromString(string, validate);
    }

    public Message(String string, DataDictionary dd) throws InvalidMessage {
            initFromString(string, dd);
    }

    private void initFromString(String string, boolean validate) 
        throws InvalidMessage {

        create();
        header = new Header();
        trailer = new Trailer();
        fromString(string, validate);
    }

    private void initFromString(String string, DataDictionary dd) 
        throws InvalidMessage {
        
        create();
        header = new Header();
        trailer = new Trailer();
        fromString(string, dd);
    }

    protected Message(Header header, Trailer trailer) {
        create();
        this.header = header;
        this.trailer = trailer;
    }

    public native static boolean InitializeXML(String url);

    protected void finalize() {
        destroy();
    }

    private native void create();
    private native void destroy();

    public native Object clone();

    public native void addGroup(Group group);
    public native Group getGroup(int num, Group group) throws FieldNotFound;

    public native void setString(int field, String value);
    public native void setBoolean(int field, boolean value);
    public native void setChar(int field, char value);
    public native void setInt(int field, int value);
    public native void setDouble(int field, double value);
    public native void setUtcTimeStamp(int field, Date value);
    public native void setUtcTimeOnly(int field, Date value);
    public native void setUtcDate(int field, Date value);

    public native String getString(int field) throws FieldNotFound;
    public native boolean getBoolean(int field) throws FieldNotFound;
    public native char getChar(int field) throws FieldNotFound;
    public native int getInt(int field) throws FieldNotFound;
    public native double getDouble(int field) throws FieldNotFound;
    public native Date getUtcTimeStamp(int field) throws FieldNotFound;
    public native Date getUtcTimeOnly(int field) throws FieldNotFound;
    public native Date getUtcDate(int field) throws FieldNotFound;

    public void setField(StringField field) {
        setString( field.getField(), field.getValue() );
    }
    public void setField(BooleanField field) {
        setBoolean( field.getField(), field.getValue() );
    }
    public void setField(CharField field) {
        setChar( field.getField(), field.getValue() );
    }
    public void setField(IntField field) {
        setInt( field.getField(), field.getValue() );
    }
    public void setField(DoubleField field) {
        setDouble( field.getField(), field.getValue() );
    }
    public void setField(UtcTimeStampField field) {
        setUtcTimeStamp( field.getField(), field.getValue() );
    }
    public void setField(UtcTimeOnlyField field) {
        setUtcTimeOnly( field.getField(), field.getValue() );
    }
    public void setField(UtcDateField field) {
        setUtcDate( field.getField(), field.getValue() );
    }

    public StringField getField(StringField field) throws FieldNotFound {
        field.setValue(getString(field.getField()));
        return field;
    }
    public BooleanField getField(BooleanField field) throws FieldNotFound {
        field.setValue(getBoolean(field.getField()));
        return field;
    }
    public CharField getField(CharField field) throws FieldNotFound {
        field.setValue(getChar(field.getField()));
        return field;
    }
    public IntField getField(IntField field) throws FieldNotFound {
        field.setValue(getInt(field.getField()));
        return field;
    }
    public DoubleField getField(DoubleField field) throws FieldNotFound {
        field.setValue(getDouble(field.getField()));
        return field;
    }
    public UtcTimeStampField getField(UtcTimeStampField field) throws FieldNotFound {
        field.setValue(getUtcTimeStamp(field.getField()));
        return field;
    }
    public UtcTimeOnlyField getField(UtcTimeOnlyField field) throws FieldNotFound {
        field.setValue(getUtcTimeOnly(field.getField()));
        return field;
    }
    public UtcDateField getField(UtcDateField field) throws FieldNotFound {
        field.setValue(getUtcDate(field.getField()));
        return field;
    }

    public native boolean isSetField(int field);
    public boolean isSetField(Field field) {
        return isSetField( field.getField() );
    }
    public native void removeField(int field);

    public java.util.Iterator iterator() {
        return new Iterator( this );
    }
    
    public native String toString();
    public native String toXML();
    private native void fromString(String string, boolean validate) throws InvalidMessage;
    private native void fromString(String string, DataDictionary dd) throws InvalidMessage;

    public final Header getHeader() {
        return header;
    }
    public final Trailer getTrailer() {
        return trailer;
    }

    public class Iterator implements java.util.Iterator {
        private Message message;
        private int cppPointer;

        public Iterator( Message aMessage ) {
            message = aMessage;
            messageIteratorCreate( this );
        }

        public boolean hasNext() {
            return messageIteratorHasNext( this );
        }

        public Object next() {
            return messageIteratorNext( this );
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    
    public class Header extends FieldMap {

        public class Iterator implements java.util.Iterator {
            private Header header;
            private int cppPointer;
            
            public Iterator( Header aHeader ) {
                header = aHeader;
            }
            
            public boolean hasNext() {
                return headerIteratorHasNext();
            }
            
            public Object next() {
                return headerIteratorNext();
            }
            
            public void remove() {}
        }

        public void setString(int field, String value) {
            headerSetString( field, value );
        }
        public void setBoolean(int field, boolean value) {
            headerSetBoolean( field, value );
        }
        public void setChar(int field, char value) {
            headerSetChar( field, value );
        }
        public void setInt(int field, int value) {
            headerSetInt( field, value );
        }
        public void setDouble(int field, double value) {
            headerSetDouble( field, value );
        }
        public void setUtcTimeStamp(int field, Date value) {
            headerSetUtcTimeStamp( field, value );
        }
        public void setUtcTimeOnly(int field, Date value) {
            headerSetUtcTimeOnly( field, value );
        }
        public void setUtcDate(int field, Date value) {
            headerSetUtcDate( field, value );
        }

        public String getString(int field) throws FieldNotFound {
            return headerGetString( field );
        }
        public boolean getBoolean(int field) throws FieldNotFound {
            return headerGetBoolean( field );
        }
        public char getChar(int field) throws FieldNotFound {
            return headerGetChar( field );
        }
        public int getInt(int field) throws FieldNotFound {
            return headerGetInt( field );
        }
        public double getDouble(int field) throws FieldNotFound {
            return headerGetDouble( field );
        }
        public Date getUtcTimeStamp(int field) throws FieldNotFound {
            return headerGetUtcTimeStamp( field );
        }
        public Date getUtcTimeOnly(int field) throws FieldNotFound {
            return headerGetUtcTimeOnly( field );
        }
        public Date getUtcDate(int field) throws FieldNotFound {
            return headerGetUtcDate( field );
        }

        public void setField(StringField field) {
            headerSetString( field.getField(), field.getValue() );
        }
        public void setField(BooleanField field) {
            headerSetBoolean( field.getField(), field.getValue() );
        }
        public void setField(CharField field) {
            headerSetChar( field.getField(), field.getValue() );
        }
        public void setField(IntField field) {
            headerSetInt( field.getField(), field.getValue() );
        }
        public void setField(DoubleField field) {
            headerSetDouble( field.getField(), field.getValue() );
        }
        public void setField(UtcTimeStampField field) {
            headerSetUtcTimeStamp( field.getField(), field.getValue() );
        }
        public void setField(UtcTimeOnlyField field) {
            headerSetUtcTimeOnly( field.getField(), field.getValue() );
        }
        public void setField(UtcDateField field) {
            headerSetUtcDate( field.getField(), field.getValue() );
        }

        public StringField getField(StringField field) throws FieldNotFound {
            field.setValue(getString(field.getField()));
            return field;
        }
        public BooleanField getField(BooleanField field) throws FieldNotFound {
            field.setValue(getBoolean(field.getField()));
            return field;
        }
        public CharField getField(CharField field) throws FieldNotFound {
            field.setValue(getChar(field.getField()));
            return field;
        }
        public IntField getField(IntField field) throws FieldNotFound {
            field.setValue(getInt(field.getField()));
            return field;
        }
        public DoubleField getField(DoubleField field) throws FieldNotFound {
            field.setValue(getDouble(field.getField()));
            return field;
        }
        public UtcTimeStampField getField(UtcTimeStampField field) throws FieldNotFound {
            field.setValue(getUtcTimeStamp(field.getField()));
            return field;
        }
        public UtcTimeOnlyField getField(UtcTimeOnlyField field) throws FieldNotFound {
            field.setValue(getUtcTimeOnly(field.getField()));
            return field;
        }
        public UtcDateField getField(UtcDateField field) throws FieldNotFound {
            field.setValue(getUtcDate(field.getField()));
            return field;
        }

        public boolean isSetField(int field) {
            return headerIsSetField( field );
        }
        public boolean isSetField(Field field) {
            return headerIsSetField( field.getField() );
        }
        public void removeField(int field) {
            headerRemoveField( field );
        }

        public java.util.Iterator iterator() {
            return new Iterator( this );
        }
    }

    public class Trailer extends FieldMap {

        public class Iterator implements java.util.Iterator {
            private Trailer trailer;
            private int cppPointer;
            
            public Iterator( Trailer aTrailer ) {
                trailer = aTrailer;
            }
            
            public boolean hasNext() {
                return trailerIteratorHasNext();
            }
            
            public Object next() {
                return trailerIteratorNext();
            }
            
            public void remove() {}
        }

        public void setString(int field, String value) {
            trailerSetString( field, value );
        }
        public void setBoolean(int field, boolean value) {
            trailerSetBoolean( field, value );
        }
        public void setChar(int field, char value) {
            trailerSetChar( field, value );
        }
        public void setInt(int field, int value) {
            trailerSetInt( field, value );
        }
        public void setDouble(int field, double value) {
            trailerSetDouble( field, value );
        }
        public void setUtcTimeStamp(int field, Date value) {
            trailerSetUtcTimeStamp( field, value );
        }
        public void setUtcTimeOnly(int field, Date value) {
            trailerSetUtcTimeOnly( field, value );
        }
        public void setUtcDate(int field, Date value) {
            trailerSetUtcDate( field, value );
        }

        public String getString(int field) throws FieldNotFound {
            return trailerGetString( field );
        }
        public boolean getBoolean(int field) throws FieldNotFound {
            return trailerGetBoolean( field );
        }
        public char getChar(int field) throws FieldNotFound {
            return trailerGetChar( field );
        }
        public int getInt(int field) throws FieldNotFound {
            return trailerGetInt( field );
        }
        public double getDouble(int field) throws FieldNotFound {
            return trailerGetDouble( field );
        }
        public Date getUtcTimeStamp(int field) throws FieldNotFound {
            return trailerGetUtcTimeStamp( field );
        }
        public Date getUtcTimeOnly(int field) throws FieldNotFound {
            return trailerGetUtcTimeOnly( field );
        }
        public Date getUtcDate(int field) throws FieldNotFound {
            return trailerGetUtcDate( field );
        }

        public void setField(StringField field) {
            trailerSetString( field.getField(), field.getValue() );
        }
        public void setField(BooleanField field) {
            trailerSetBoolean( field.getField(), field.getValue() );
        }
        public void setField(CharField field) {
            trailerSetChar( field.getField(), field.getValue() );
        }
        public void setField(IntField field) {
            trailerSetInt( field.getField(), field.getValue() );
        }
        public void setField(DoubleField field) {
            trailerSetDouble( field.getField(), field.getValue() );
        }
        public void setField(UtcTimeStampField field) {
            trailerSetUtcTimeStamp( field.getField(), field.getValue() );
        }
        public void setField(UtcTimeOnlyField field) {
            trailerSetUtcTimeOnly( field.getField(), field.getValue() );
        }
        public void setField(UtcDateField field) {
            trailerSetUtcDate( field.getField(), field.getValue() );
        }

        public StringField getField(StringField field) throws FieldNotFound {
            field.setValue(getString(field.getField()));
            return field;
        }
        public BooleanField getField(BooleanField field) throws FieldNotFound {
            field.setValue(getBoolean(field.getField()));
            return field;
        }
        public CharField getField(CharField field) throws FieldNotFound {
            field.setValue(getChar(field.getField()));
            return field;
        }
        public IntField getField(IntField field) throws FieldNotFound {
            field.setValue(getInt(field.getField()));
            return field;
        }
        public DoubleField getField(DoubleField field) throws FieldNotFound {
            field.setValue(getDouble(field.getField()));
            return field;
        }
        public UtcTimeStampField getField(UtcTimeStampField field) throws FieldNotFound {
            field.setValue(getUtcTimeStamp(field.getField()));
            return field;
        }
        public UtcTimeOnlyField getField(UtcTimeOnlyField field) throws FieldNotFound {
            field.setValue(getUtcTimeOnly(field.getField()));
            return field;
        }
        public UtcDateField getField(UtcDateField field) throws FieldNotFound {
            field.setValue(getUtcDate(field.getField()));
            return field;
        }

        public boolean isSetField(int field) {
            return trailerIsSetField( field );
        }
        public boolean isSetField(Field field) {
            return trailerIsSetField( field.getField() );
        }
        public void removeField(int field) {
            trailerRemoveField( field );
        }

        public java.util.Iterator iterator() {
            return new Iterator( this );
        }
    }

    private native Iterator messageIteratorCreate( Iterator i );
    private native boolean messageIteratorHasNext( Iterator i );
    private native Object messageIteratorNext( Iterator i );

    private native void headerSetString(int field, String value);
    private native void headerSetBoolean(int field, boolean value);
    private native void headerSetChar(int field, char value);
    private native void headerSetInt(int field, int value);
    private native void headerSetDouble(int field, double value);
    private native void headerSetUtcTimeStamp(int field, Date value);
    private native void headerSetUtcTimeOnly(int field, Date value);
    private native void headerSetUtcDate(int field, Date value);
    
    private native String headerGetString(int field) throws FieldNotFound;
    private native boolean headerGetBoolean(int field) throws FieldNotFound;
    private native char headerGetChar(int field) throws FieldNotFound;
    private native int headerGetInt(int field) throws FieldNotFound;
    private native double headerGetDouble(int field) throws FieldNotFound;
    private native Date headerGetUtcTimeStamp(int field) throws FieldNotFound;
    private native Date headerGetUtcTimeOnly(int field) throws FieldNotFound;
    private native Date headerGetUtcDate(int field) throws FieldNotFound;

    private native boolean headerIsSetField(int field);
    private native void headerRemoveField(int field);

    private native boolean headerIteratorHasNext();
    private native Object headerIteratorNext();

    private native void trailerSetString(int field, String value);
    private native void trailerSetBoolean(int field, boolean value);
    private native void trailerSetChar(int field, char value);
    private native void trailerSetInt(int field, int value);
    private native void trailerSetDouble(int field, double value);
    private native void trailerSetUtcTimeStamp(int field, Date value);
    private native void trailerSetUtcTimeOnly(int field, Date value);
    private native void trailerSetUtcDate(int field, Date value);
    
    private native String trailerGetString(int field) throws FieldNotFound;
    private native boolean trailerGetBoolean(int field) throws FieldNotFound;
    private native char trailerGetChar(int field) throws FieldNotFound;
    private native int trailerGetInt(int field) throws FieldNotFound;
    private native double trailerGetDouble(int field) throws FieldNotFound;
    private native Date trailerGetUtcTimeStamp(int field) throws FieldNotFound;
    private native Date trailerGetUtcTimeOnly(int field) throws FieldNotFound;
    private native Date trailerGetUtcDate(int field) throws FieldNotFound;

    private native boolean trailerIsSetField(int field);
    private native void trailerRemoveField(int field);

    private native boolean trailerIteratorHasNext();
    private native Object trailerIteratorNext();
}
