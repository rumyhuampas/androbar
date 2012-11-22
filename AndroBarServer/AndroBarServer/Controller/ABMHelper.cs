using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using AndroBarServer.Model;
using System.Collections;

namespace AndroBarServer.Controller
{
    public static class ABMHelper
    {
        public const string OS_RECEIVED = "RECEIVED";
        public const string OS_PENDING = "PENDING";
        public const string OS_DELIVERED = "DELIVERED";
        public const string OS_CANCEL_REQUESTED = "CANCEL_REQUESTED";
        public const string OS_CANCELED = "CANCELED";
        public const string OS_CHARGE_REQUESTED = "CHARGE_REQUESTED";
        public const string OS_CHARGED = "CHARGED";

        public static class OrderStatusComboList
        {
            public static object[] GetList(){
                object[] col = new object[4];
                col[0] = new ABMHelper.ComboBoxItem("Recibido", OS_RECEIVED);
                col[1] = new ABMHelper.ComboBoxItem("Pendiente", OS_PENDING);
                col[2] = new ABMHelper.ComboBoxItem("Entregado", OS_DELIVERED);
                col[3] = new ABMHelper.ComboBoxItem("Cancelado", OS_CANCELED);
                return col;
            }

            public static int GetItemIndex(string value)
            {
                switch (value)
                {
                    case OS_RECEIVED: return 0;
                    case OS_PENDING: return 1;
                    case OS_DELIVERED: return 2;
                    case OS_CANCELED: return 3;
                    default: return 0;
                }
            }

            public static string GetItemValue(int index)
            {
                switch (index)
                {
                    case 0: return OS_RECEIVED;
                    case 1: return OS_PENDING;
                    case 3: return OS_DELIVERED;
                    case 4: return OS_CANCELED;
                    default: return OS_RECEIVED;
                }
            }
        }

        public class ComboBoxItem
        {
            public string Text { get; set; }
            public object Value { get; set; }

            public ComboBoxItem(string text, object value)
            {
                Text = text;
                Value = value;
            }

            public override string ToString()
            {
                return Text;
            }
        }

        public static category GetCategory(int id)
        {
            return GetCategory(null, id);
        }

        public static category GetCategory(androbarEntities db, int id)
        {
            if (db == null) db = new androbarEntities();
            try
            {
                return (from categories in db.categories
                        where categories.Id == id
                        select categories).First();
            }
            catch
            {
                return null;
            }
        }

        public static product GetProduct(int id)
        {
            return GetProduct(null, id);
        }

        public static product GetProduct(androbarEntities db, int id)
        {
            if (db == null) db = new androbarEntities();
            try
            {
                return (from products in db.products
                        where products.Id == id
                        select products).First();
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Reads data from a stream until the end is reached. The
        /// data is returned as a byte array. An IOException is
        /// thrown if any of the underlying IO calls fail.
        /// </summary>
        /// <param name="stream">The stream to read data from</param>
        /// <param name="initialLength">The initial buffer length</param>
        public static byte[] StreamToByteArray(Stream stream, int initialLength)
        {
            // If we've been passed an unhelpful initial length, just
            // use 32K.
            if (initialLength < 1)
            {
                initialLength = 32768;
            }

            byte[] buffer = new byte[initialLength];
            int read = 0;

            int chunk;
            while ((chunk = stream.Read(buffer, read, buffer.Length - read)) > 0)
            {
                read += chunk;

                // If we've reached the end of our buffer, check to see if there's
                // any more information
                if (read == buffer.Length)
                {
                    int nextByte = stream.ReadByte();

                    // End of stream? If so, we're done
                    if (nextByte == -1)
                    {
                        return buffer;
                    }

                    // Nope. Resize the buffer, put in the byte we've just
                    // read, and continue
                    byte[] newBuffer = new byte[buffer.Length * 2];
                    Array.Copy(buffer, newBuffer, buffer.Length);
                    newBuffer[read] = (byte)nextByte;
                    buffer = newBuffer;
                    read++;
                }
            }
            // Buffer is now too big. Shrink it.
            byte[] ret = new byte[read];
            Array.Copy(buffer, ret, read);
            return ret;
        }
    }
}
